package com.example.be_dolan_final.service;

import com.example.be_dolan_final.config.Constant;
import com.example.be_dolan_final.dto.ExchangeDTO;
import com.example.be_dolan_final.entities.ExchangeDetails;
import com.example.be_dolan_final.entities.Exchanges;
import com.example.be_dolan_final.entities.Orders;
import com.example.be_dolan_final.mapper.MapperUtils;
import com.example.be_dolan_final.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.be_dolan_final.config.Constant.OderStatus.Delivery;

@Service
@AllArgsConstructor
public class ExchangeService {

    private final IExchangeRepository exchangeRepository;
    private final IExchangeDetailsRepository exchangeDetailsRepository;
    private final IProductDetailRepository productDetailRepository;
    private final IProductRepository productRepository;
    private final UserService userService;
    private final IOrderRepository orderRepository;

    public ExchangeDTO createdExchange(ExchangeDTO exchangeDTO) {
        Orders order = this.orderRepository.findByIdOrThrow(exchangeDTO.getOrderId(), "Oder not found");
        if (Delivery.equals(order.getStatus())) {
            Exchanges save = this.exchangeRepository.save(MapperUtils.map(exchangeDTO, Exchanges.class));
            exchangeDTO.getExchangeDetailsDTOS().forEach(x -> {
                x.setExchangeId(save.getId());
                x.setStatus(Constant.ExchangesStatus.WaitFor);
            });
            this.exchangeDetailsRepository.saveAll(MapperUtils.mapList(exchangeDTO.getExchangeDetailsDTOS(), ExchangeDetails.class));
            
            return MapperUtils.map(save, ExchangeDTO.class);
        }
        throw new RuntimeException("Đơn hàng không đúng trạng thái để đổi trả");
    }

}
