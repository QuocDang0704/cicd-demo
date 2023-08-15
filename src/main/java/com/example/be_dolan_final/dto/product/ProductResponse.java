package com.example.be_dolan_final.dto.product;

import com.example.be_dolan_final.dto.projection.ProductHome;
import com.example.be_dolan_final.dto.projection.ProductPJ;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;

    private String name;

    private String material;

    private String describe;

    private Long categoryId;

    private String categoryName;

    private Long brandId;

    private String brandName;

    private String image;

    private Boolean active;

    private BigDecimal price;

    private Set<OptionResponse> customProductDetails;

    private BigDecimal appliedPercentage;

    private BigDecimal discountedPrice;

    public static ProductResponse fromTo(ProductPJ productPJ) {
        List<OptionResponse> data = new Gson()
                .fromJson(productPJ.getCustomProductDetails(), new TypeToken<ArrayList<OptionResponse>>() {
                }.getType());
        Set<OptionResponse> dataSet = new HashSet<>(data);
        return ProductResponse.builder()
                .id(productPJ.getId())
                .name(productPJ.getName())
                .material(productPJ.getMaterial())
                .describe(productPJ.getDescribe())
                .categoryId(productPJ.getCategoryId())
                .categoryName(productPJ.getCategoryName())
                .brandId(productPJ.getBrandId())
                .brandName(productPJ.getBrandName())
                .image(productPJ.getImage())
                .active(productPJ.getActive())
                .price(productPJ.getPrice())
                .customProductDetails(dataSet)
                .build();
    }

    public static ProductResponse fromTo(ProductHome productHome) {
        return ProductResponse.builder()
                .id(productHome.getId())
                .name(productHome.getName())
                .material(productHome.getMaterial())
                .describe(productHome.getDescribe())
                .categoryId(productHome.getCategoryId())
                .brandId(productHome.getBrandId())
                .image(productHome.getImage())
                .active(productHome.getActive())
                .price(productHome.getPrice())
                .appliedPercentage(productHome.getAppliedPercentage())
                .discountedPrice(productHome.getDiscountedPrice())
                .build();
    }
}
