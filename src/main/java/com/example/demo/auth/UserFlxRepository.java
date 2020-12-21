package com.example.demo.auth;

import org.springframework.data.repository.CrudRepository;

public interface UserFlxRepository extends CrudRepository<UserFlx, Long> {
    UserFlx findByUsername(String username);



}
