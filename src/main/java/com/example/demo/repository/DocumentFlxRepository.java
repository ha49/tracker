package com.example.demo.repository;

import com.example.demo.entity.ClientFlx;
import com.example.demo.entity.DocumentFlx;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentFlxRepository extends CrudRepository<DocumentFlx, Long> {
    DocumentFlx findByName(String name);

    Iterable<DocumentFlx> findByClientFlx(ClientFlx clientFlx);
}
