package com.example.be_dolan_final.dto;

import com.example.be_dolan_final.config.Constant;
import com.example.be_dolan_final.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private String code;
    private String note;
    private String payed;
    private String payment;
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    private Constant.OderStatus status;
    private BigDecimal shipPrice;
    private String shopMethod;
    private BigDecimal shipPriceDiscount;
    private String describe;

    private Long userId;
    private String addressName;
    private Long voucherId;
    private String nameUser;
    private String phone;
    private List<OrderDetailsDTO> orderDetailsDTOS;
}
