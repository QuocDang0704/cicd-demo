package com.example.be_dolan_final.dto;

import com.example.be_dolan_final.config.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Boolean active;
    private Constant.UserRole role;
    private String dob;
}
