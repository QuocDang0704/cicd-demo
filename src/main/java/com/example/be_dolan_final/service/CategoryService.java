package com.example.be_dolan_final.service;

import com.example.be_dolan_final.entities.Category;
import com.example.be_dolan_final.repository.ICategoryRepository;
import com.example.be_dolan_final.config.exception.NotFoundException;
import com.example.be_dolan_final.dto.CategoryDTO;
import com.example.be_dolan_final.mapper.MapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    private final ICategoryRepository categoryRepository;

    public Page<CategoryDTO> getAll(String name, Pageable pageable) {
        Page<Category> categories = this.categoryRepository.findAllByNameContains(name, pageable);
        return MapperUtils.mapEntityPageIntoDtoPage(categories, CategoryDTO.class);
    }

    public CategoryDTO getById(Long id) {
        return MapperUtils.map(this.categoryRepository.findByIdOrThrow(id), CategoryDTO.class);
    }

    public CategoryDTO createdCategory(CategoryDTO categoryDTO) {
        Category category = MapperUtils.map(categoryDTO, Category.class);
        this.categoryRepository.save(category);
        return categoryDTO;
    }

    public CategoryDTO updatedCategory(CategoryDTO categoryDTO) {
        this.categoryRepository.findById(categoryDTO.getId())
                .map(category -> {
                    category.setName(categoryDTO.getName());
                    category.setStatus(categoryDTO.getStatus());
                    return this.categoryRepository.save(category);
                }).orElseThrow(() -> new NotFoundException("Category not found"));
        return categoryDTO;
    }

    public void deletedCategory(Long id) {
        Category category = this.categoryRepository.findByIdOrThrow(id);
        this.categoryRepository.deleteById(category.getId());
    }

}
