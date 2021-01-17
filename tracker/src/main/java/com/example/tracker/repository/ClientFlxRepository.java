package com.example.tracker.repository;


import com.example.tracker.entity.ClientFlx;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientFlxRepository extends CrudRepository<ClientFlx, Long> {
    ClientFlx findByLastName(String lastName);

    //    Iterable<ClientFlx> findByCoachFlx(CoachFlx coachFlx);

     /*
    @Query("SELECT cl FROM ClientFlx cl inner join ClientCoachMembershipFlx ms on ms.clientFlx.id = cl.id " +
            " WHERE  ms.coachFlx.id = :coachId")
    Iterable<ClientFlx>  findClientFlxByStatusAndCoachIdParams(
            @Param("status") String status,
            @Param("coachId") Long coachId);*/
}
