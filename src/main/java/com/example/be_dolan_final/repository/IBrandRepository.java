package com.example.be_dolan_final.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.be_dolan_final.entities.Brand;
import com.example.be_dolan_final.repository.custom.IBaseRepository;

@Repository
public interface IBrandRepository extends IBaseRepository<Brand, Long> {
    Page<Brand> findAllByActive(Pageable pageable, Boolean active);

    Page<Brand> findAllByNameContains(Pageable pageable, String name);

}
