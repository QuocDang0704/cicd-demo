package com.example.be_dolan_final.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomSizeDTO {
    private Long sizeId;
    private Long quantity;
}
