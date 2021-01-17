package com.example.tracker.repository;

import com.example.tracker.entity.ComboContentFlx;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboContentFlxRepository extends CrudRepository<ComboContentFlx, Integer> {
    Iterable<ComboContentFlx> findByName(String name);
}
