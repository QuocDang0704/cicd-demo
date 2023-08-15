package com.example.be_dolan_final.dto.projection;

import java.math.BigDecimal;

public interface ProductHome {
    Long getId();
    String getName();
    String getMaterial();
    String getDescribe();
    Long getCategoryId();
    Long getBrandId();
    String getImage();
    Boolean getActive();
    BigDecimal getPrice();
    BigDecimal getAppliedPercentage();
    BigDecimal getDiscountedPrice();
}
