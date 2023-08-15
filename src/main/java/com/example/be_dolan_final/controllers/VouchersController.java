package com.example.be_dolan_final.controllers;

import java.math.BigDecimal;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.be_dolan_final.dto.ResponseDTO;
import com.example.be_dolan_final.dto.VouchersDTO;
import com.example.be_dolan_final.service.VouchersService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/dolan/vouchers")
public class VouchersController {
    private final VouchersService vouchersService;

    @GetMapping("/get-all")
    public ResponseDTO getAll(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "money", defaultValue = "") BigDecimal money,
            Pageable pageable) {
        return ResponseDTO.success(this.vouchersService.getAll(name, money, pageable));
    }

    @PostMapping("/create")
    public ResponseDTO createVouchers(@RequestBody VouchersDTO vouchersDTO) {
        return ResponseDTO.success(this.vouchersService.createVouchers(vouchersDTO));
    }

    @PutMapping("/update")
    public ResponseDTO updateVouchers(@RequestBody VouchersDTO vouchersDTO) {
        return ResponseDTO.success(this.vouchersService.updateVouchers(vouchersDTO));
    }

    @DeleteMapping("/delete")
    public ResponseDTO deleteVouchers(@RequestBody VouchersDTO vouchersDTO) {
        this.vouchersService.deleteVouchers(vouchersDTO);
        return ResponseDTO.success();
    }
}
