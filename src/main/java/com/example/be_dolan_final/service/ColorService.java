package com.example.be_dolan_final.service;

import com.example.be_dolan_final.config.exception.NotFoundException;
import com.example.be_dolan_final.dto.ColorDTO;
import com.example.be_dolan_final.entities.Color;
import com.example.be_dolan_final.mapper.MapperUtils;
import com.example.be_dolan_final.repository.IColorRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ColorService {
    private final IColorRepository colorRepository;

    public Page<ColorDTO> getAll(Pageable pageable, String name) {
        Page<Color> color = this.colorRepository.findAllByNameContains(pageable, name);
        return MapperUtils.mapEntityPageIntoDtoPage(color, ColorDTO.class);
    }

    public ColorDTO getOne(Long id) {
        Color color = this.colorRepository.findByIdOrThrow(id);
        return MapperUtils.map(color, ColorDTO.class);
    }

    public ColorDTO searchNameColor(String name) {
        Color color = this.colorRepository.findByNameContainingIgnoreCase(name)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy bản ghi"));
        return MapperUtils.map(color, ColorDTO.class);
    }

    public ColorDTO createdColor(ColorDTO colorDTO) {
        Color color = MapperUtils.map(colorDTO, Color.class);
        this.colorRepository.save(color);
        return colorDTO;
    }

    public ColorDTO updatedColor(ColorDTO colorDTO) {
        this.colorRepository.findById(colorDTO.getId())
                .map(color -> {
                    color.setActive(colorDTO.isActive());
                    color.setHex(colorDTO.getHex());
                    color.setName(colorDTO.getName());
                    return this.colorRepository.save(color);
                }).orElseThrow(() -> new NotFoundException("Color not found"));
        return colorDTO;
    }

    public void deleteColor(Long id) {
        Color color = this.colorRepository.findByIdOrThrow(id);
        this.colorRepository.deleteById(color.getId());
    }
}
