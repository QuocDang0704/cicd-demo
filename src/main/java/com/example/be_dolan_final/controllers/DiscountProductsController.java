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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.be_dolan_final.dto.DiscountProductsDTO;
import com.example.be_dolan_final.dto.ResponseDTO;
import com.example.be_dolan_final.service.DiscountProductsService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/dolan/discount-products")
@AllArgsConstructor
public class DiscountProductsController {
    private final DiscountProductsService discountProductsService;

    @GetMapping("/get-all")
    public ResponseDTO getAll(
            Pageable pageable
    ) {
        return ResponseDTO.success(this.discountProductsService.getAll(pageable));
    }
    @PostMapping("/create") 
    public ResponseDTO create(
        @RequestBody DiscountProductsDTO discountProductsDTO 
    ) {
    	return ResponseDTO.success(this.discountProductsService.create(discountProductsDTO));
    }
    
    @PutMapping("/update")
    public ResponseDTO update(
        @RequestBody DiscountProductsDTO discountProductsDTO 
    ) {
    	return ResponseDTO.success(this.discountProductsService.update(discountProductsDTO));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseDTO delete(@PathVariable("id") Long id) {
        this.discountProductsService.deleteBrand(id);
        return ResponseDTO.success();
    }
    
}
