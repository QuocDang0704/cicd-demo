package com.example.be_dolan_final.dto;

import com.example.be_dolan_final.config.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {
    private Long id;
    private Long productDetailsId;
    private Long orderId;
    private Long quantity;
    private BigDecimal price;
    private String name;
    private String image;
    @Enumerated(EnumType.STRING)
    private Constant.OderDetails status;
}
