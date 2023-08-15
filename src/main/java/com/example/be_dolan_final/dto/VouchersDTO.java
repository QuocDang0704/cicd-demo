package com.example.be_dolan_final.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VouchersDTO {
    private Long id;
    private boolean active;
    private String code;
    private BigDecimal money;
    private String name;
    private Integer slot;
}
