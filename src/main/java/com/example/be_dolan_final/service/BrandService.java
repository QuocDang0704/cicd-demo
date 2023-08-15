package com.example.be_dolan_final.service;

import java.io.FileNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.be_dolan_final.dto.BrandDTO;
import com.example.be_dolan_final.entities.Brand;
import com.example.be_dolan_final.mapper.MapperUtils;
import com.example.be_dolan_final.repository.IBrandRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BrandService {
    private final IBrandRepository brandRepository;

    // public Page<BrandDTO> getAll(Pageable pageable) {
    //     Page<Brand> brand = this.brandRepository.findAll(pageable);
    //     return MapperUtils.mapEntityPageIntoDtoPage(brand, BrandDTO.class);
    // }
    public Page<BrandDTO> getAll(String name, Pageable pageable) {
        Page<Brand> brand = this.brandRepository.findAllByNameContains(pageable, name);
        return MapperUtils.mapEntityPageIntoDtoPage(brand, BrandDTO.class);
    }

    public Page<BrandDTO> getAllActive(Pageable pageable, Boolean active) {
        Page<Brand> brand = this.brandRepository.findAllByActive(pageable, active);
        return MapperUtils.mapEntityPageIntoDtoPage(brand, BrandDTO.class);
    }

    public BrandDTO getById(Long id) {
        Brand brand = this.brandRepository.findById(id)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException(String.format("Not found with id: %s", id));
                });
        return MapperUtils.map(brand, BrandDTO.class);
    }

    public BrandDTO createdBrand(BrandDTO brandDTO) {
        Brand brand = MapperUtils.map(brandDTO, Brand.class);
        return MapperUtils.map(this.brandRepository.save(brand), BrandDTO.class);
    }

    public BrandDTO updateBrand(BrandDTO brandDTO) {
        // check if brand exist
        getById(brandDTO.getId());

        Brand brand = MapperUtils.map(brandDTO, Brand.class);
        return MapperUtils.map(this.brandRepository.save(brand), BrandDTO.class);
    }

    public void deleteBrand(Long id) {
        // check if brand exist
        getById(id);
        
        try {
            this.brandRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Can't delete brand with id: %s", id));
        };
    }
}
