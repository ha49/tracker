package com.example.tracker.repository;

import com.example.tracker.auth.UserFlx;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserFlxRepository extends CrudRepository<UserFlx, Long> {
    UserFlx findByUsername(String username);

    @Override
    Optional<UserFlx> findById(Long id);


}
