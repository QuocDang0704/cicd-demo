package com.example.be_dolan_final.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.be_dolan_final.dto.DiscountCategoryDTO;
import com.example.be_dolan_final.entities.Category;
import com.example.be_dolan_final.entities.DiscountCategory;
import com.example.be_dolan_final.mapper.MapperUtils;
import com.example.be_dolan_final.repository.ICategoryRepository;
import com.example.be_dolan_final.repository.IDiscountCategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DiscountCategoryService {
    private final IDiscountCategoryRepository discountCategoryRepository;
    private final ICategoryRepository categoryRepository;
    
    public Page<DiscountCategoryDTO> getAll(Pageable pageable) {
        Page<DiscountCategory> discountCategory = this.discountCategoryRepository.findAll(pageable);
        Page<DiscountCategoryDTO> discountCategoryDTO = MapperUtils.mapEntityPageIntoDtoPage(discountCategory, DiscountCategoryDTO.class);
        discountCategoryDTO.forEach(discountCategoryDTO1 -> {
            Category category = this.categoryRepository.findById(discountCategoryDTO1.getCategoryId()).orElse(null);
            discountCategoryDTO1.setCategory(category);
        });
        return discountCategoryDTO;
    }
    public DiscountCategoryDTO create(DiscountCategoryDTO discountCategoryDTO) {
        DiscountCategory discountCategory = MapperUtils.map(discountCategoryDTO, DiscountCategory.class);
        discountCategoryDTO.getListCategoryId().forEach(categoryId -> {
            this.discountCategoryRepository.deleteByCategoryId(categoryId);
            
            discountCategory.setCategoryId(categoryId);
            this.discountCategoryRepository.save(discountCategory);
        });
        return discountCategoryDTO;
    }
    public DiscountCategoryDTO update(DiscountCategoryDTO discountCategoryDTO) {
        // check if brand exist
        getById(discountCategoryDTO.getId());

        DiscountCategory discountCategory = MapperUtils.map(discountCategoryDTO, DiscountCategory.class);
        return MapperUtils.map(this.discountCategoryRepository.save(discountCategory), DiscountCategoryDTO.class);
    }
    public void deleteBrand(Long id) {
        // check if brand exist
        getById(id);
        
        try {
            this.discountCategoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Can't delete with id: %s", id));
        }
    }
    public DiscountCategoryDTO getById(Long id) {
        DiscountCategory discountCategory = this.discountCategoryRepository.findById(id).orElse(null);
        if (discountCategory == null) {
            throw new IllegalArgumentException(String.format("Không thể tìm thấy khuyến mãi theo danh mục: %s", id));
        }
        return MapperUtils.map(discountCategory, DiscountCategoryDTO.class);
    }
}
