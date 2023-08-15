package com.example.be_dolan_final.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomProductDetails {
    private Long id;
    private Long productId;
    private Long sizeId;
    private Long colorId;
    private boolean active;
    private String describe;
    private String material;
    private Long quantity;
    private List<CustomSizeDTO> sizes;
}
