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
import com.example.be_dolan_final.dto.product.ProductDTO;
import com.example.be_dolan_final.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/dolan/product")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/get-all")
    public ResponseDTO getAll(Pageable pageable) {
        return ResponseDTO.success(this.productService.getAll(pageable));
    }

    @GetMapping("/get/{id}")
    public ResponseDTO getOne(@PathVariable("id") Long id) {
        return ResponseDTO.success(this.productService.getOne(id));
    }

    @PostMapping("/create")
    public ResponseDTO create(@RequestBody ProductDTO productDTO) {
        return ResponseDTO.success(this.productService.createdProduct(productDTO));
    }

    @PutMapping("/update")
    public ResponseDTO update(@RequestBody ProductDTO productDTO) {
        return ResponseDTO.success(this.productService.updatedProduct(productDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO getAll(@PathVariable("id") Long id) {
        this.productService.deleteProduct(id);
        return ResponseDTO.success();
    }

    @GetMapping("/all-price")
    public ResponseDTO getAllByPrice(Pageable pageable) {

        return ResponseDTO.success(this.productService.findAllByPrice(pageable));
    }

    @GetMapping("/all-date")
    public ResponseDTO getAllByDate(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            Pageable pageable) {
        return ResponseDTO.success(this.productService.findAllByDate(name, minPrice, maxPrice, pageable));
    }

}
