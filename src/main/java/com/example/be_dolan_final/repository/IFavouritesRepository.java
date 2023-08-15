package com.example.be_dolan_final.repository;

import org.springframework.stereotype.Repository;

import com.example.be_dolan_final.entities.Favourites;
import com.example.be_dolan_final.repository.custom.IBaseRepository;

@Repository
public interface IFavouritesRepository extends IBaseRepository<Favourites, Long> {
    
}
