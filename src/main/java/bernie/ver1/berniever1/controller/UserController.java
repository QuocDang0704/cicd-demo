package bernie.ver1.berniever1.controller;

import bernie.ver1.berniever1.dto.ResponseDTO;
import bernie.ver1.berniever1.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getAllUsers")
    public ResponseDTO getAllUsers(Pageable pageable) {
        return ResponseDTO.success(this.userService.getAllUsers(pageable));
    }

    @GetMapping("/exportAllUsersToExcel")
    public ResponseEntity<?> exportAllUsersToExcel() {
        return this.userService.exportAllUsersToExcel();
    }

    @GetMapping("/temp")
    public ResponseEntity<?> temp() {
        return this.userService.temp();
    }
}
