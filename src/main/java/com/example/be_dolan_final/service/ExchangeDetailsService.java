package com.example.be_dolan_final.service;

import com.example.be_dolan_final.config.Constant;
import com.example.be_dolan_final.dto.ExchangeDetailsDTO;
import com.example.be_dolan_final.entities.ExchangeDetails;
import com.example.be_dolan_final.mapper.MapperUtils;
import com.example.be_dolan_final.repository.IExchangeDetailsRepository;
import com.example.be_dolan_final.repository.IExchangeRepository;
import com.example.be_dolan_final.repository.IProductDetailRepository;
import com.example.be_dolan_final.repository.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.be_dolan_final.config.Constant.ExchangesStatus.Accept;
import static com.example.be_dolan_final.config.Constant.ExchangesStatus.Reject;

@Service
@AllArgsConstructor
public class ExchangeDetailsService {
    private final IExchangeRepository exchangeRepository;
    private final IExchangeDetailsRepository exchangeDetailsRepository;
    private final IProductDetailRepository productDetailRepository;
    private final IProductRepository productRepository;
    private final UserService userService;

    public ExchangeDetailsDTO createdExchangeDetails(ExchangeDetailsDTO exchangeDetailsDTO) {
        this.exchangeDetailsRepository.save(MapperUtils.map(exchangeDetailsDTO, ExchangeDetails.class));
        return exchangeDetailsDTO;
    }

    public List<ExchangeDetailsDTO> createdExchangeDetailsList(List<ExchangeDetailsDTO> exchangeDetailsDTOS) {
        exchangeDetailsDTOS.forEach(x -> {
            x.setStatus(Constant.ExchangesStatus.WaitFor);
        });
        this.exchangeDetailsRepository.saveAll(MapperUtils.mapList(exchangeDetailsDTOS, ExchangeDetails.class));
        return exchangeDetailsDTOS;
    }

    public void updateStatusAccept(Long id) {
        ExchangeDetails exchangeDetails = this.exchangeDetailsRepository.findByIdOrThrow(id);
        exchangeDetails.setStatus(Accept);
        this.exchangeDetailsRepository.save(exchangeDetails);
    }

    public void updateStatusReject(Long id) {
        ExchangeDetails exchangeDetails = this.exchangeDetailsRepository.findByIdOrThrow(id);
        exchangeDetails.setStatus(Reject);
        this.exchangeDetailsRepository.save(exchangeDetails);
    }
}
