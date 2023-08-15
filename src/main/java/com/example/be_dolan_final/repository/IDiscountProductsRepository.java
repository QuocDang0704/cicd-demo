package com.example.be_dolan_final.repository;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.be_dolan_final.entities.DiscountProducts;
import com.example.be_dolan_final.repository.custom.IBaseRepository;

@Repository
public interface IDiscountProductsRepository extends IBaseRepository<DiscountProducts, Long> {

    @Modifying
    @Query(value = "DELETE FROM DiscountProducts dp WHERE dp.productId = :productId")
    @Transactional
    void deleteByProductId(@Param("productId") Long productId);

}
