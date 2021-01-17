package com.example.tracker.repository;

import com.example.tracker.entity.CoachFlx;
import com.example.tracker.entity.LinkFlx;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkFlxRepository extends CrudRepository<LinkFlx, Long> {
    LinkFlx findByDescription(String description);

    Iterable<LinkFlx> findByCoachFlx(CoachFlx coachFlx);
}
