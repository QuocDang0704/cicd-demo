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

import com.example.be_dolan_final.dto.BrandDTO;
import com.example.be_dolan_final.dto.ResponseDTO;
import com.example.be_dolan_final.service.BrandService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/dolan/brand")
@AllArgsConstructor
public class BrandController {
    private final BrandService brandService;

    // @GetMapping("/get-all")
    // public ResponseDTO getAll(Pageable pageable) {
    // return ResponseDTO.success(this.brandService.getAll(pageable));
    // }
    @GetMapping("/get-all")
    public ResponseDTO getAll(
            @RequestParam(value = "name", defaultValue = "") String name,
            Pageable pageable) {
        return ResponseDTO.success(this.brandService.getAll(name, pageable));
    }

    @GetMapping("/get-all-active")
    public ResponseDTO getAllActive(Pageable pageable, Boolean active) {
        return ResponseDTO.success(this.brandService.getAllActive(pageable, active));
    }

    @GetMapping("/get/{id}")
    public ResponseDTO getById(@PathVariable("id") Long id) {
        return ResponseDTO.success(this.brandService.getById(id));
    }

    @PostMapping("/create")
    public ResponseDTO create(@RequestBody BrandDTO brandDTO) {
        return ResponseDTO.success(this.brandService.createdBrand(brandDTO));
    }

    @PutMapping("/update")
    public ResponseDTO update(@RequestBody BrandDTO brandDTO) {
        return ResponseDTO.success(this.brandService.updateBrand(brandDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO delete(@PathVariable("id") Long id) {
        this.brandService.deleteBrand(id);
        return ResponseDTO.success();
    }

}
