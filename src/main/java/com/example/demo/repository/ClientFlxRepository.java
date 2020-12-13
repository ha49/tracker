package com.example.demo.repository;


import com.example.demo.entity.ClientFlx;
import com.example.demo.entity.CoachFlx;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientFlxRepository extends CrudRepository<ClientFlx, Long> {
    ClientFlx findByLastName(String lastName);

    Iterable<ClientFlx> findByCoachFlx(CoachFlx coachFlx);

}
