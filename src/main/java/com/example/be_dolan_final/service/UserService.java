package com.example.be_dolan_final.service;

import com.example.be_dolan_final.config.Constant;
import com.example.be_dolan_final.config.exception.NotFoundException;
import com.example.be_dolan_final.dto.UserDTO;
import com.example.be_dolan_final.dto.UserDetailDTO;
import com.example.be_dolan_final.dto.response.JwtResponse;
import com.example.be_dolan_final.entities.User;
import com.example.be_dolan_final.mapper.MapperUtils;
import com.example.be_dolan_final.repository.IUserRepository;
import com.example.be_dolan_final.security.jwt.JwtUtils;
import com.example.be_dolan_final.security.service.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    private final IUserRepository userRepository;

    public Page<UserDTO> getAll(Pageable pageable) {
        Page<User> users = this.userRepository.findAll(pageable);
        return MapperUtils.mapEntityPageIntoDtoPage(users, UserDTO.class);
    }

    public UserDTO getById(Long id) {
        return MapperUtils.map(this.userRepository.findByIdOrThrow(id), UserDTO.class);
    }

    public UserDTO createdUser(UserDTO userDTO) {
        User user = MapperUtils.map(userDTO, User.class);
        user.setPassword(this.encoder.encode(user.getPassword()));
        this.userRepository.save(user);
        return userDTO;
    }

    public UserDTO updatedUser(UserDTO userDTO) {
        this.userRepository.findById(userDTO.getId())
                .map(user -> {
                    user.setLastName(userDTO.getLastName());
                    user.setFirstName(userDTO.getFirstName());
                    user.setGender(userDTO.getGender());
                    user.setUsername(userDTO.getUsername());
                    user.setPhone(userDTO.getPhone());
                    user.setEmail(userDTO.getEmail());
                    user.setDob(userDTO.getDob());
                    return this.userRepository.save(user);
                }).orElseThrow(() -> new NotFoundException("User not found"));
        return userDTO;
    }

    public void deleteUser(Long id) {
        User user = this.userRepository.findByIdOrThrow(id);
        this.userRepository.deleteById(user.getId());
    }

    public UserDetailDTO loadUserByUsername(String username) {
        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Không tìm thấy user"));
        return MapperUtils.map(user, UserDetailDTO.class);
    }

    public JwtResponse login(UserDTO payloadLogin) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        payloadLogin.getUsername(), payloadLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(jwt, "Bearer",
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles.get(0)
        );
    }

    public UserDTO registerUser(UserDTO userDTO) {
        User user = MapperUtils.map(userDTO, User.class);
        user.setPassword(this.encoder.encode(user.getPassword()));
        user.setRole(Constant.UserRole.CUSTOMER);
        this.userRepository.save(user);
        return userDTO;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        if (Objects.isNull(currentUser)) {
            return userRepository.findById(2L).orElseThrow(NotFoundException::new);
        }
        return userRepository.findByUsername(currentUser).orElseThrow(NotFoundException::new);
    }

}
