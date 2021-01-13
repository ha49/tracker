package com.example.demo.repository;

import com.example.demo.entity.ComboContentFlx;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboContentFlxRepository extends CrudRepository<ComboContentFlx, Integer> {
    Iterable<ComboContentFlx> findByName(String name);
}
