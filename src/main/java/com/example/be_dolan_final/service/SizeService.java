package com.example.be_dolan_final.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.be_dolan_final.dto.SizeDTO;
import com.example.be_dolan_final.entities.Size;
import com.example.be_dolan_final.mapper.MapperUtils;
import com.example.be_dolan_final.repository.ISizeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SizeService {
    private final ISizeRepository sizeRepository;

    public Page<SizeDTO> getAll(String name, Pageable pageable) {
        Page<Size> Size = this.sizeRepository.findAllByNameContains(pageable, name);
        return MapperUtils.mapEntityPageIntoDtoPage(Size, SizeDTO.class);
    }

    public Page<SizeDTO> getAllActive(Pageable pageable, Boolean active) {
        Page<Size> Size = this.sizeRepository.findAllByActive(pageable, active);
        return MapperUtils.mapEntityPageIntoDtoPage(Size, SizeDTO.class);
    }

    public SizeDTO getById(Long id) {
        Size Size = this.sizeRepository.findById(id)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException(String.format("Not found with id: %s", id));
                });
        return MapperUtils.map(Size, SizeDTO.class);
    }

    public SizeDTO createdSize(SizeDTO SizeDTO) {
        Size Size = MapperUtils.map(SizeDTO, Size.class);
        return MapperUtils.map(this.sizeRepository.save(Size), SizeDTO.class);
    }

    public SizeDTO updateSize(SizeDTO SizeDTO) {
        // check if Size exist
        getById(SizeDTO.getId());

        Size Size = MapperUtils.map(SizeDTO, Size.class);
        return MapperUtils.map(this.sizeRepository.save(Size), SizeDTO.class);
    }

    public void deleteSize(Long id) {
        // check if Size exist
        getById(id);
        
        try {
            this.sizeRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Can't delete Size with id: %s", id));
        };
    }
}
