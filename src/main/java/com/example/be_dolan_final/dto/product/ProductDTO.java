package com.example.be_dolan_final.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.example.be_dolan_final.entities.Color;
import com.example.be_dolan_final.entities.ProductDetails;
import com.example.be_dolan_final.entities.Products;
import com.example.be_dolan_final.entities.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private Long categoryId;
    private Long brandId;
    private Boolean active;
    private String describe;
    private String material;
    private String name;
    private BigDecimal price;
    private String image;
    private Instant createdTimestamp;
    private Instant updatedTimestamp;
    private List<CustomProductDetails> customProductDetails;
    private List<Size> sizeList;
    private List<Color> colorList;
    private List<ProductDetails> productDetailsList;
    private Integer numberOfPayment;//Số sản phẩm bán được

    public ProductDTO(Products p, Integer numberOfPayment){
        this.id = p.getId();
        this.name = p.getName();
        this.categoryId = p.getCategoryId();
        this.brandId = p.getBrandId();
        this.active = p.getActive();
        this.describe = p.getDescribe();
        this.material = p.getMaterial();
        this.price = p.getPrice();
        this.image = p.getImage();
        this.createdTimestamp = p.getCreatedTimestamp();
        this.updatedTimestamp = p.getUpdatedTimestamp();
        this.numberOfPayment = numberOfPayment;
    }
}
