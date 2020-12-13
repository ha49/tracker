package com.example.demo.repository;

import com.example.demo.entity.CoachFlx;
import com.example.demo.entity.LinkFlx;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkFlxRepository extends CrudRepository<LinkFlx, Long> {
    LinkFlx findByDescription(String description);

    Iterable<LinkFlx> findByCoachFlx(CoachFlx coachFlx);
}
