package com.example.be_dolan_final.repository;

import com.example.be_dolan_final.entities.Category;
import com.example.be_dolan_final.repository.custom.IBaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends IBaseRepository<Category, Long> {

    Page<Category> findAllByNameContains(String name, Pageable pageable);

}
