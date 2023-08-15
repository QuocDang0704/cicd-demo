package com.example.be_dolan_final.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.be_dolan_final.dto.FavouritesDTO;
import com.example.be_dolan_final.dto.ResponseDTO;
import com.example.be_dolan_final.service.FavouritesService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/dolan/favourites")
public class FavouritesController {
    private final FavouritesService favouritesService;

    @PostMapping("/create")
    public ResponseDTO create(@RequestBody FavouritesDTO favouritesDTO) {
        return ResponseDTO.success(this.favouritesService.createdFavourites(favouritesDTO));
    }

    @DeleteMapping("/delete")
    public ResponseDTO delete(Long id) {
        this.favouritesService.deleteFavourites(id);
        return ResponseDTO.success();
    }
}
