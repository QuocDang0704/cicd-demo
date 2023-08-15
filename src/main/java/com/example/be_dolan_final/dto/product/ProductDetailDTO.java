package com.example.be_dolan_final.dto.product;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDTO {
    private Long id;
    private Long productId;
    private Long sizeId;
    private String sizeName;
    private Long colorId;
    private String colorName;
    private boolean active;
    private String describe;
    private String material;
    private Long quantity;
    private List<CustomSizeDTO> sizes;
}
