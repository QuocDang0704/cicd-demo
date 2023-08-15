package com.example.be_dolan_final.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.be_dolan_final.entities.Vouchers;
import com.example.be_dolan_final.repository.custom.IBaseRepository;

@Repository
public interface IVouchersRepository extends IBaseRepository<Vouchers, Long> {
    Page<Vouchers> findAllByNameContains(Pageable pageable, String name);

    Page<Vouchers> findAllByNameContainsAndMoney(Pageable pageable, String name, BigDecimal money);

}
