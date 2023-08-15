package com.example.be_dolan_final.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vouchers")
public class Vouchers extends SharedAuditingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "active")
    private boolean active;

    @Column(name = "code")
    private String code;

    @Column(name = "money")
    private BigDecimal money;

    @Column(name = "name")
    private String name;

    @Column(name = "slot")
    private Integer slot;
}
