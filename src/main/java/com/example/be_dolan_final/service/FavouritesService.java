package com.example.be_dolan_final.service;

import org.springframework.stereotype.Service;

import com.example.be_dolan_final.dto.FavouritesDTO;
import com.example.be_dolan_final.entities.Favourites;
import com.example.be_dolan_final.mapper.MapperUtils;
import com.example.be_dolan_final.repository.IFavouritesRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FavouritesService {
    private final IFavouritesRepository favouritesRepository;

    public FavouritesDTO createdFavourites(FavouritesDTO favouritesDTO) {
        Favourites favourites = MapperUtils.map(favouritesDTO, Favourites.class);
        // get current user
        favourites.setUserId(1L);

        return MapperUtils.map(this.favouritesRepository.save(favourites), FavouritesDTO.class);
    }
    
    public void deleteFavourites(Long id) {
        this.favouritesRepository.deleteById(id);
    }
}
