package com.example.be_dolan_final.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.be_dolan_final.dto.DiscountCategoryDTO;
import com.example.be_dolan_final.dto.ResponseDTO;
import com.example.be_dolan_final.service.DiscountCategoryService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/dolan/discount-category")
@AllArgsConstructor
public class DiscountCategoryController {
    private final DiscountCategoryService discountCategoryService;

    @GetMapping("/get-all")
    public ResponseDTO getAll(
            Pageable pageable
    ) {
        return ResponseDTO.success(this.discountCategoryService.getAll(pageable));
    }
    @PostMapping("/create") 
    public ResponseDTO create(
        @RequestBody DiscountCategoryDTO discountCategoryDTO 
    ) {
    	return ResponseDTO.success(this.discountCategoryService.create(discountCategoryDTO));
    }
    @PutMapping("/update")
    public ResponseDTO update(
        @RequestBody DiscountCategoryDTO discountCategoryDTO 
    ) {
    	return ResponseDTO.success(this.discountCategoryService.update(discountCategoryDTO));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseDTO delete(@PathVariable("id") Long id) {
        this.discountCategoryService.deleteBrand(id);
        return ResponseDTO.success();
    }


}
