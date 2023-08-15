package com.example.be_dolan_final.repository;

import com.example.be_dolan_final.entities.ExchangeDetails;
import com.example.be_dolan_final.repository.custom.IBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IExchangeDetailsRepository extends IBaseRepository<ExchangeDetails, Long> {
    Optional<ExchangeDetails> findFirstByOrderDetailId(Long orderDetailsId);
}
