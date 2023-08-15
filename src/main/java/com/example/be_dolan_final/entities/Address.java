package com.example.be_dolan_final.entities;

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
@Table(name = "address")
public class Address extends SharedAuditingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "active")
    private boolean active;

    @Column(name = "province")
    private String province;

    @Column(name = "province_id")
    private String provinceId;

    @Column(name = "district")
    private String district;

    @Column(name = "district_id")
    private String districtId;

    @Column(name = "ward")
    private String ward;

    @Column(name = "war_id")
    private String wardId;

    @Column(name = "address")
    private String address;

    @Column(name = "is_default")
    private boolean isDefault;
}
