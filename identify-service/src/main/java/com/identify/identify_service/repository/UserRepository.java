package com.identify.identify_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.identify.identify_service.entity.User;

@Repository// Annotation này đánh dấu interface là một repository, cho phép Spring tự động tạo ra một implementation của interface này để thực hiện các thao tác CRUD trên entity User.
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);

}
