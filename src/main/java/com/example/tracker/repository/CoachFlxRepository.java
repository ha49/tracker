package com.example.tracker.repository;


import com.example.tracker.entity.CoachFlx;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachFlxRepository extends CrudRepository<CoachFlx, Long> {
    CoachFlx findByLastName(String lastName);

    CoachFlx getCoachFlxByUserFlx_Username(String username);
}
