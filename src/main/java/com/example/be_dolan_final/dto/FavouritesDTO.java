package com.example.be_dolan_final.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavouritesDTO {
    private Long id;
    private Long productId;
    private Long userId;
}
