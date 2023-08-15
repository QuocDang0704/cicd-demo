package com.example.be_dolan_final.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.be_dolan_final.entities.Address;
import com.example.be_dolan_final.repository.custom.IBaseRepository;

import java.util.Optional;

@Repository
public interface IAddressRepository extends IBaseRepository<Address, Long> {
    // find all address by user id
    Page<Address> findAllByUserId(Long userId, Pageable pageable);

    Optional<Address> findFirstByAddress(String address);
}
