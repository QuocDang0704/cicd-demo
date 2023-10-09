package bernie.ver1.berniever1.service;

import bernie.ver1.berniever1.entities.User;
import bernie.ver1.berniever1.repository.UserRepository;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final ExcelService excelService;

    public List<User> getAllUsers(Pageable pageable) {
        return this.userRepository.findAll();
    }

    public ResponseEntity<?> exportAllUsersToExcel() {
        List<String> headers = List.of("Id", "UserName", "Email", "Phone", "Password", "Roles");
        List<Object[]> data = this.userRepository
                .findAll()
                .stream()
                .map(user -> new Object[]{
                        user.getId().toString(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPhone(),
                        user.getPassword(),
                        user.getRole().toString()
                }).toList();
        try {
            byte[] excelData = excelService.exportDataToExcel(headers, data);
            ByteArrayInputStream bis = new ByteArrayInputStream(excelData);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Disposition", "attachment; filename=data.xlsx");

            return ResponseEntity
                    .ok()
                    .headers(httpHeaders)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(bis));
        } catch (Exception e) {
            LogFactory.getLog(UserService.class).error(e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }

    public ResponseEntity<?> temp() {
        return ResponseEntity
                .ok()
                .body("temp");
    }
}
