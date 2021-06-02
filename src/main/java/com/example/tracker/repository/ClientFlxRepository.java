package com.example.tracker.repository;


import com.example.tracker.entity.ClientFlx;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientFlxRepository extends CrudRepository<ClientFlx, Long> {
    ClientFlx findByLastName(String lastName);

    ClientFlx getClientFlxById(Long id);

    ClientFlx getClientFlxByUserFlx(String username);

    ClientFlx getClientFlxByUserFlx_Username(String username);


//    void uploadClientPhoto(long id, MultipartFile file);

//  Iterable <ClientFlx>getClientFlxByUserFlx(UserFlx userFlx);

//    @Override
//    Optional<ClientFlx> findById(Long aLong);

    //    Iterable<ClientFlx> findByCoachFlx(CoachFlx coachFlx);

     /*
    @Query("SELECT cl FROM ClientFlx cl inner join ClientCoachMembershipFlx ms on ms.clientFlx.id = cl.id " +
            " WHERE  ms.coachFlx.id = :coachId")
    Iterable<ClientFlx>  findClientFlxByStatusAndCoachIdParams(
            @Param("status") String status,
            @Param("coachId") Long coachId);*/
}
