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

import com.example.be_dolan_final.dto.AddressDTO;
import com.example.be_dolan_final.dto.ResponseDTO;
import com.example.be_dolan_final.service.AddressService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/dolan/address")
@AllArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/get-all")
    public ResponseDTO getAll(Pageable pageable) {
        return ResponseDTO.success(this.addressService.getAll(pageable));
    }

    @PostMapping("/create")
    public ResponseDTO create(@RequestBody AddressDTO addressDTO) {
        return ResponseDTO.success(this.addressService.createdAddress(addressDTO));
    }

    @PutMapping("/update")
    public ResponseDTO update(@RequestBody AddressDTO addressDTO) {
        return ResponseDTO.success(this.addressService.updateAddress(addressDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO delete(@PathVariable("id") Long id) {
        this.addressService.deleteAddress(id);
        return ResponseDTO.success();
    }
    @PutMapping("/set-default/{id}")
    public ResponseDTO setDefault(@PathVariable("id") Long id) {
        this.addressService.setDefault(id);
        return ResponseDTO.success();
    }
}
