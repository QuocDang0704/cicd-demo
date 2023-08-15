package com.example.be_dolan_final.dto;

import java.util.Date;
import java.util.List;

import com.example.be_dolan_final.entities.Products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiscountProductsDTO {
    private Long id;
    private Long productId;
    private boolean status;
    private String percentage;
    private String describe;
    private Date effectiveStartDate;
    private Date effectiveEndDate;
    private List<Long> listProductId;
    private Products products;
}
