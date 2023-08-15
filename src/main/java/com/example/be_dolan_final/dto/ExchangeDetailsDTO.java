package com.example.be_dolan_final.dto;

import com.example.be_dolan_final.config.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeDetailsDTO {
    private Long id;
    private Long exchangeId;
    private Long orderDetailId;
    private Long quantity;
    @Enumerated(EnumType.STRING)
    private Constant.ExchangesStatus status;
}
