package com.example.be_dolan_final.service;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.be_dolan_final.dto.VouchersDTO;
import com.example.be_dolan_final.entities.Vouchers;
import com.example.be_dolan_final.mapper.MapperUtils;
import com.example.be_dolan_final.repository.IVouchersRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VouchersService {
    private final IVouchersRepository vouchersRepository;
    
    public Page<VouchersDTO> getAll(String name, BigDecimal money, Pageable pageable) {
        if (money == null) {
            Page<Vouchers> vouchers = this.vouchersRepository.findAllByNameContains(pageable, name);
            return MapperUtils.mapEntityPageIntoDtoPage(vouchers, VouchersDTO.class);
        }

        Page<Vouchers> vouchers = this.vouchersRepository.findAllByNameContainsAndMoney(pageable, name, money);
        return MapperUtils.mapEntityPageIntoDtoPage(vouchers, VouchersDTO.class);
    }

    public VouchersDTO createVouchers(VouchersDTO vouchersDTO) {
        Vouchers vouchers = MapperUtils.map(vouchersDTO, Vouchers.class);
        return MapperUtils.map(this.vouchersRepository.save(vouchers), VouchersDTO.class);
    }

    public VouchersDTO updateVouchers(VouchersDTO vouchersDTO) {
        Vouchers vouchers = MapperUtils.map(vouchersDTO, Vouchers.class);
        return MapperUtils.map(this.vouchersRepository.save(vouchers), VouchersDTO.class);
    }

    public void deleteVouchers(VouchersDTO vouchersDTO) {
        Vouchers vouchers = MapperUtils.map(vouchersDTO, Vouchers.class);
        this.vouchersRepository.delete(vouchers);
    }
}
