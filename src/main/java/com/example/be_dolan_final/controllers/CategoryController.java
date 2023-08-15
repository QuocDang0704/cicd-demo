package com.example.be_dolan_final.controllers;

import com.example.be_dolan_final.dto.CategoryDTO;
import com.example.be_dolan_final.dto.ResponseDTO;
import com.example.be_dolan_final.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dolan/category")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get-all")
    public ResponseDTO getAll(
            @RequestParam(value = "name", defaultValue = "") String name,
            Pageable pageable
    ) {
        return ResponseDTO.success(this.categoryService.getAll(name, pageable));
    }

    @GetMapping("/get/{id}")
    public ResponseDTO getById(@PathVariable("id") Long id) {
        return ResponseDTO.success(this.categoryService.getById(id));
    }

    @PostMapping("/create")
    public ResponseDTO create(@RequestBody CategoryDTO categoryDTO) {
        return ResponseDTO.success(this.categoryService.createdCategory(categoryDTO));
    }

    @PutMapping("/update")
    public ResponseDTO update(@RequestBody CategoryDTO categoryDTO) {
        return ResponseDTO.success(this.categoryService.updatedCategory(categoryDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO delete(@PathVariable("id") Long id) {
        this.categoryService.deletedCategory(id);
        return ResponseDTO.success();
    }
}
