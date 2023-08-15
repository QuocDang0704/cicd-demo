package com.example.be_dolan_final.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.be_dolan_final.entities.ProductDetails;
import com.example.be_dolan_final.repository.custom.IBaseRepository;

@Repository
public interface IProductDetailRepository extends IBaseRepository<ProductDetails, Long> {
    List<ProductDetails> findByProductId(Long productId);

    ProductDetails findByProductIdAndColorIdAndSizeId(Long productId, Long colorId, Long sizeId);
}
