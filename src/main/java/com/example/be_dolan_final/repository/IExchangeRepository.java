package com.example.be_dolan_final.repository;

import com.example.be_dolan_final.entities.Exchanges;
import com.example.be_dolan_final.repository.custom.IBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IExchangeRepository extends IBaseRepository<Exchanges,Long> {
    Optional<Exchanges> findFirstByOrderId(Long orderId);
}
