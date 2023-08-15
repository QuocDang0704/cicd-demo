package com.example.be_dolan_final.dto.projection;

import java.math.BigDecimal;

public interface ProductPJ {

    Long getId();

    String getName();

    String getMaterial();

    String getDescribe();

    Long getCategoryId();

    String getCategoryName();

    Long getBrandId();

    String getBrandName();

    String getImage();

    Boolean getActive();

    BigDecimal getPrice();

    String getCustomProductDetails();
}
