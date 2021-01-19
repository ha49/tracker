
package com.example.tracker.repository;

import com.example.tracker.entity.TrackingFlx;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface TrackingFlxRepository extends CrudRepository<TrackingFlx, Long> {

    TrackingFlx findById(long id);

    //Iterable<TrackingFlx> findByClientFlx(ClientFlx clientFlx);

    Iterable<TrackingFlx> findByClientCoachMembershipFlx(long membershipId);

    Iterable<TrackingFlx> findByTrackingDate(Date date);
    //   @Query("SELECT i FROM TrackingFlx i where i.flxClient.id = :flxClientId")
    //   Iterable<TrackingFlx> findTrackingByFlxClientId(Long flxClientId);
}

