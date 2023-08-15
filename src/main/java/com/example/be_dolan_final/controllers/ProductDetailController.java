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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.be_dolan_final.dto.ResponseDTO;
import com.example.be_dolan_final.dto.product.ProductDetailDTO;
import com.example.be_dolan_final.service.ProductDetailService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/dolan/product_details")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ProductDetailController {
    private final ProductDetailService productDetailService;

    @GetMapping("/get-all")
    public ResponseDTO getAll(Pageable pageable) {
        return ResponseDTO.success(this.productDetailService.getAll(pageable));
    }

    @GetMapping("/get/{id}")
    public ResponseDTO getOne(@PathVariable("id") Long id) {
        return ResponseDTO.success(this.productDetailService.getOne(id));
    }

    @PostMapping("/create")
    public ResponseDTO create(@RequestBody ProductDetailDTO productDetailDTO) {
        return ResponseDTO.success(this.productDetailService.createdProductDetail(productDetailDTO));
    }

    @PutMapping("/update")
    public ResponseDTO update(@RequestBody ProductDetailDTO productDetailDTO) {
        return ResponseDTO.success(this.productDetailService.updatedProductDetail(productDetailDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO getAll(@PathVariable("id") Long id) {
        this.productDetailService.deleteProductDetail(id);
        return ResponseDTO.success();
    }

    @GetMapping("/product-color-size")
    public ResponseDTO findByProductAndSizeAndColor(@RequestParam Long productId, @RequestParam Long colorId, @RequestParam Long sizeId) {
        return ResponseDTO.success(this.productDetailService.findByProductAndSizeAndColor(productId, colorId, sizeId));
    }
}
