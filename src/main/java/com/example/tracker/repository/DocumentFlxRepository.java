package com.example.tracker.repository;

import com.example.tracker.entity.ClientFlx;
import com.example.tracker.entity.DocumentFlx;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentFlxRepository extends CrudRepository<DocumentFlx, Long> {
    DocumentFlx findByName(String name);

    Iterable<DocumentFlx> findByClientFlx(ClientFlx clientFlx);
}
