package com.example.be_dolan_final.dto;

import com.example.be_dolan_final.config.Constant;
import com.example.be_dolan_final.entities.SharedAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailDTO extends SharedAuditingEntity {
    private Long id;
    private String fullname;
    private String email;
    private String phone;

    private String username;
    private String password;
    private Constant.UserRole role;

}