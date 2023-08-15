package com.example.be_dolan_final.entities;

import com.example.be_dolan_final.config.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends SharedAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String gender;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private Boolean active;
    @Column
    @Enumerated(EnumType.STRING)
    private Constant.UserRole role;
    @Column
    private String dob;
}
