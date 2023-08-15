package com.example.be_dolan_final.repository;

import com.example.be_dolan_final.entities.OrderDetails;
import com.example.be_dolan_final.repository.custom.IBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDetailsRepository extends IBaseRepository<OrderDetails, Long> {

    List<OrderDetails> findAllByOrderId(Long id);

}
