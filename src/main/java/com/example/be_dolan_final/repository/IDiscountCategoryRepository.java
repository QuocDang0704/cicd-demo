package com.example.be_dolan_final.repository;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.be_dolan_final.entities.DiscountCategory;
import com.example.be_dolan_final.repository.custom.IBaseRepository;

@Repository
public interface IDiscountCategoryRepository extends IBaseRepository<DiscountCategory, Long> {

    @Modifying
    @Query(value = "DELETE FROM DiscountCategory dc WHERE dc.categoryId = :categoryId")
    @Transactional
    void deleteByCategoryId(@Param("categoryId") Long categoryId);
}
