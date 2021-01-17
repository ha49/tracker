package com.example.tracker.repository;

import com.example.tracker.auth.UserFlx;
import org.springframework.data.repository.CrudRepository;

public interface UserFlxRepository extends CrudRepository<UserFlx, Long> {
    UserFlx findByUsername(String username);



}
