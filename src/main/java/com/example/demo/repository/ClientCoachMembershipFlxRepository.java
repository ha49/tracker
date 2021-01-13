package com.example.demo.repository;

import com.example.demo.entity.ClientCoachMembershipFlx;
import com.example.demo.entity.ClientFlx;
import com.example.demo.entity.CoachFlx;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientCoachMembershipFlxRepository extends CrudRepository<ClientCoachMembershipFlx, Long> {
    Iterable<ClientCoachMembershipFlx> findByClientFlx(ClientFlx clientFlx);

    Iterable<ClientCoachMembershipFlx> findByCoachFlx(CoachFlx coachFlx);

    @Query("SELECT cl FROM ClientFlx cl inner join ClientCoachMembershipFlx ms on ms.clientFlx.id = cl.id " +

//            " WHERE  ms.coachFlx.id = :coachId and cl.status = :status"

            " WHERE  ms.coachFlx.id = :coachId ")
    Iterable<ClientFlx>  findClientFlxByStatusAndCoachIdParams(
//            @Param("status") String status,
            @Param("coachId") Long coachId);
}
