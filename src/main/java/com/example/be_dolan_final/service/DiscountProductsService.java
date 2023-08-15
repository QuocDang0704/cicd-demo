package com.example.be_dolan_final.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.be_dolan_final.dto.DiscountProductsDTO;
import com.example.be_dolan_final.entities.DiscountProducts;
import com.example.be_dolan_final.entities.Products;
import com.example.be_dolan_final.mapper.MapperUtils;
import com.example.be_dolan_final.repository.IDiscountProductsRepository;
import com.example.be_dolan_final.repository.IProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DiscountProductsService {
    private final IDiscountProductsRepository discountProductsRepository;
    private final IProductRepository productRepository;

    public Page<DiscountProductsDTO> getAll(Pageable pageable) {
        Page<DiscountProducts> discountProducts = this.discountProductsRepository.findAll(pageable);
        Page<DiscountProductsDTO> discountProductsDTO = MapperUtils.mapEntityPageIntoDtoPage(discountProducts, DiscountProductsDTO.class);
        discountProductsDTO.forEach(discountProductsDTO1 -> {
            Products products = this.productRepository.findById(discountProductsDTO1.getProductId()).orElse(null);
            discountProductsDTO1.setProducts(products);
        });
        return discountProductsDTO;
    }

    public DiscountProductsDTO create(DiscountProductsDTO discountProductsDTO) {
        DiscountProducts discountProducts = MapperUtils.map(discountProductsDTO, DiscountProducts.class);
        discountProductsDTO.getListProductId().forEach(productId -> {
            this.discountProductsRepository.deleteByProductId(productId);
            
            discountProducts.setProductId(productId);
            this.discountProductsRepository.save(discountProducts);
        });
        return discountProductsDTO;
    }

    public DiscountProductsDTO update(DiscountProductsDTO discountProductsDTO) {
        // check if brand exist
        getById(discountProductsDTO.getId());

        DiscountProducts discountProducts = MapperUtils.map(discountProductsDTO, DiscountProducts.class);
        return MapperUtils.map(this.discountProductsRepository.save(discountProducts), DiscountProductsDTO.class);
    }

    public void deleteBrand(Long id) {
        // check if brand exist
        getById(id);
        
        try {
            this.discountProductsRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Can't delete with id: %s", id));
        }
    }
    public DiscountProductsDTO getById(Long id) {
        DiscountProducts discountProducts = this.discountProductsRepository.findById(id)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException(String.format("Not found with id: %s", id));
                });
        return MapperUtils.map(discountProducts, DiscountProductsDTO.class);
    }
    
}
