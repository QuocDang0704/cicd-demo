package com.example.be_dolan_final.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BrandDTO {
    private Long id;
    private String code;
    private String name;
    private boolean active;
}
