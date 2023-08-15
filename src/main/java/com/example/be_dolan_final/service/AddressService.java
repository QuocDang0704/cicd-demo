package com.example.be_dolan_final.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.be_dolan_final.dto.AddressDTO;
import com.example.be_dolan_final.entities.Address;
import com.example.be_dolan_final.mapper.MapperUtils;
import com.example.be_dolan_final.repository.IAddressRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddressService {
    private final IAddressRepository addressRepository;

    public Page<AddressDTO> getAll(Pageable pageable) {
        // fake user id
        Long userId = 1L;

        Page<Address> address = this.addressRepository.findAllByUserId(userId, pageable);
        return MapperUtils.mapEntityPageIntoDtoPage(address, AddressDTO.class);
    }

    public AddressDTO createdAddress(AddressDTO addressDTO) {
        Address address = MapperUtils.map(addressDTO, Address.class);
        // fake user id
        address.setUserId(1L);
        return MapperUtils.map(this.addressRepository.save(address), AddressDTO.class);
    }

    public AddressDTO updateAddress(AddressDTO addressDTO) {
        Address address = MapperUtils.map(addressDTO, Address.class);
        // fake user id
        address.setUserId(1L);
        return MapperUtils.map(this.addressRepository.save(address), AddressDTO.class);
    }

    public void deleteAddress(Long id) {
        this.addressRepository.deleteById(id);
    }
    public void setDefault(Long id) {
        // fake user id
        Long userId = 1L;
        Page<Address> address = this.addressRepository.findAllByUserId(userId, Pageable.unpaged());
        address.forEach(item -> {
            if (item.getId() == id) {
                item.setDefault(true);
            } else {
                item.setDefault(false);
            }
            this.addressRepository.save(item);
        });
        return ;
    }
}
