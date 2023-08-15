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
import com.example.be_dolan_final.dto.SizeDTO;
import com.example.be_dolan_final.service.SizeService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/dolan/size")
@AllArgsConstructor
public class SizeController {
    private final SizeService sizeService;

    @GetMapping("/get-all")
    public ResponseDTO getAll(
            @RequestParam(value = "name", defaultValue = "") String name,
            Pageable pageable) {
        return ResponseDTO.success(this.sizeService.getAll(name, pageable));
    }
    
    @GetMapping("/get/{id}")
    public ResponseDTO getById(@PathVariable("id") Long id) {
        return ResponseDTO.success(this.sizeService.getById(id));
    }

    @PostMapping("/create")
    public ResponseDTO create(@RequestBody SizeDTO sizeDTO) {
        return ResponseDTO.success(this.sizeService.createdSize(sizeDTO));
    }

    @PutMapping("/update")
    public ResponseDTO update(@RequestBody SizeDTO sizeDTO) {
        return ResponseDTO.success(this.sizeService.updateSize(sizeDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO delete(@PathVariable("id") Long id) {
        this.sizeService.deleteSize(id);
        return ResponseDTO.success();
    }
}
