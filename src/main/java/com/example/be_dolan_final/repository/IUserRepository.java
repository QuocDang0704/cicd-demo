package com.example.be_dolan_final.repository;

import com.example.be_dolan_final.entities.User;
import com.example.be_dolan_final.repository.custom.IBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends IBaseRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Long countAllByActive(Boolean active);

}
