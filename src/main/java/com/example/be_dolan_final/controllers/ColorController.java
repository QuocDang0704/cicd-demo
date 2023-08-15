package com.example.be_dolan_final.controllers;

import com.example.be_dolan_final.dto.ColorDTO;
import com.example.be_dolan_final.dto.ResponseDTO;
import com.example.be_dolan_final.service.ColorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dolan/color")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ColorController {
    private final ColorService colorService;

    @GetMapping("/get-all")
    public ResponseDTO getAll(
        @RequestParam(value = "name", defaultValue = "") String name,
        Pageable pageable
        ) {
        return ResponseDTO.success(this.colorService.getAll(pageable, name));
    }

    @GetMapping("/get/{id}")
    public ResponseDTO getOne(@PathVariable("id") Long id) {
        return ResponseDTO.success(this.colorService.getOne(id));
    }

    @GetMapping("/search/{name}")
    public ResponseDTO searchName(@PathVariable("name") String name) {
        return ResponseDTO.success(this.colorService.searchNameColor(name));
    }

    @PostMapping("/create")
    public ResponseDTO create(@RequestBody ColorDTO colorDTO) {
        return ResponseDTO.success(this.colorService.createdColor(colorDTO));
    }

    @PutMapping("/update")
    public ResponseDTO update(@RequestBody ColorDTO colorDTO) {
        return ResponseDTO.success(this.colorService.updatedColor(colorDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO getAll(@PathVariable("id") Long id) {
        this.colorService.deleteColor(id);
        return ResponseDTO.success();
    }
}
