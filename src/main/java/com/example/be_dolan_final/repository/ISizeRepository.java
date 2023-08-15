package com.example.be_dolan_final.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.be_dolan_final.entities.Size;
import com.example.be_dolan_final.repository.custom.IBaseRepository;

@Repository
public interface ISizeRepository extends IBaseRepository<Size, Long> {
    Page<Size> findAllByActive(Pageable pageable, Boolean active);

    Page<Size> findAllByNameContains(Pageable pageable, String name);
}
