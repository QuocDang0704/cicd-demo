package com.example.be_dolan_final.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StatisticalResponse {
    private BigDecimal sumMoneyRevenue;
    private BigDecimal sumMoneyReturn;
    private BigDecimal sumMoneyWaitForConfirmation;
    private BigDecimal sumMoneyPreparingGoods;
    private BigDecimal sumMoneyDelivery;
    private BigDecimal sumMoneySuccess;
    private BigDecimal sumMoneyChangeOrReturn;
    private BigDecimal sumMoneyCancel;
}
