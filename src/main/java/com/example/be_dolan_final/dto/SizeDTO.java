package com.example.be_dolan_final.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SizeDTO {
    private Long id;
    private boolean active;
    private String name;
}

