package com.example.demo.repository;


import com.example.demo.entity.CoachFlx;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachFlxRepository extends CrudRepository<CoachFlx, Long> {
    CoachFlx findByLastName(String lastName);
}
