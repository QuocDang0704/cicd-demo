package com.example.be_dolan_final.repository;

import com.example.be_dolan_final.entities.Color;
import com.example.be_dolan_final.repository.custom.IBaseRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IColorRepository extends IBaseRepository<Color, Long> {
    Page<Color> findAllByNameContains(Pageable pageable, String name);
    
    Optional<Color> findByNameContainingIgnoreCase(String name);
}
