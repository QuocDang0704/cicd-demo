package com.example.be_dolan_final.controllers;

import com.example.be_dolan_final.dto.ResponseDTO;
import com.example.be_dolan_final.dto.UserDTO;
import com.example.be_dolan_final.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dolan/user")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @GetMapping("/get-all")
    public ResponseDTO getAll(Pageable pageable) {
        return ResponseDTO.success(this.userService.getAll(pageable));
    }

    @GetMapping("/get/{id}")
    public ResponseDTO getById(@PathVariable("id") Long id) {
        return ResponseDTO.success(this.userService.getById(id));
    }

    @PostMapping("/create")
    public ResponseDTO create(@RequestBody UserDTO userDTO) {
        return ResponseDTO.success(this.userService.createdUser(userDTO));
    }

    @PutMapping("/update")
    public ResponseDTO update(@RequestBody UserDTO userDTO) {
        return ResponseDTO.success(this.userService.updatedUser(userDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO getAll(@PathVariable("id") Long id) {
        this.userService.deleteUser(id);
        return ResponseDTO.success();
    }

    @PostMapping("/register")
    public ResponseDTO register(@RequestBody UserDTO userDTO) {
        return ResponseDTO.success(this.userService.registerUser(userDTO));
    }

    @PostMapping("/login")
    public ResponseDTO login(@RequestBody UserDTO userDTO) {
        return ResponseDTO.success(this.userService.login(userDTO));
    }
}
