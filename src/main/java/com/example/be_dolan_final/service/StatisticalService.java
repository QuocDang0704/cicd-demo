package com.example.be_dolan_final.service;

import com.example.be_dolan_final.dto.response.StatisticalResponse;
import com.example.be_dolan_final.repository.IOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;

@Service
@AllArgsConstructor
public class StatisticalService {

    private final IOrderRepository orderRepository;

    public StatisticalResponse getStatiscal(String startDate, String endDate) throws ParseException {
        BigDecimal sumMoneyRevenue = this.orderRepository.sumMoneyRevenue(startDate, endDate);
        BigDecimal sumMoneyReturn = this.orderRepository.sumMoneyReturn(startDate, endDate);
        BigDecimal waitForConfirmation = this.orderRepository.sumQuantityStatisticalOrder("WaitForConfirmation");
        BigDecimal preparingGoods = this.orderRepository.sumQuantityStatisticalOrder("PreparingGoods");
        BigDecimal delivery = this.orderRepository.sumQuantityStatisticalOrder("Delivery");
        BigDecimal success = this.orderRepository.sumQuantityStatisticalOrder("Success");
        BigDecimal changeOrReturn = this.orderRepository.sumQuantityStatisticalOrder("ChangeOrReturn");
        BigDecimal cancel = this.orderRepository.sumQuantityStatisticalOrder("Cancel");
        return StatisticalResponse.builder()
                .sumMoneyRevenue(sumMoneyRevenue)
                .sumMoneyReturn(sumMoneyReturn)
                .sumMoneyWaitForConfirmation(waitForConfirmation)
                .sumMoneyPreparingGoods(preparingGoods)
                .sumMoneyDelivery(delivery)
                .sumMoneySuccess(success)
                .sumMoneyChangeOrReturn(changeOrReturn)
                .sumMoneyCancel(cancel)
                .build();
    }


}
