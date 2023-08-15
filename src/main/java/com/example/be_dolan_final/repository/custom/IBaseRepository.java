package com.example.be_dolan_final.repository.custom;

import com.example.be_dolan_final.config.exception.NotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IBaseRepository<T, ID> extends JpaRepository<T, ID> {

    default T findByIdOrThrow(@NotNull ID id, String message) {
        return findById(id).orElseThrow(() -> new NotFoundException(message));
    }

    default T findByIdOrThrow(@NotNull ID id) {
        return findByIdOrThrow(id, "Không tìm thấy bản ghi. ");
    }

    boolean existsById(ID id);
}
