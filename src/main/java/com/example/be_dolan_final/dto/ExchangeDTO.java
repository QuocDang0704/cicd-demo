package com.example.be_dolan_final.dto;

import com.example.be_dolan_final.config.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeDTO {
    private Long id;
    private Long orderId;
    private String reason; // của khách hàng
    @Enumerated(EnumType.STRING)
    private Constant.ExchangesType type;

    private List<ExchangeDetailsDTO> exchangeDetailsDTOS;
}
