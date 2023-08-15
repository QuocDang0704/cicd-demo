package com.example.be_dolan_final.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColorDTO {
    private Long id;
    private boolean active;
    private String hex;
    private String name;
}
