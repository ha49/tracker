
package com.example.demo.repository;

import com.example.demo.entity.ClientFlx;
import com.example.demo.entity.TrackingFlx;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingFlxRepository extends CrudRepository<TrackingFlx, Long> {

    TrackingFlx findById(long id);

    Iterable<TrackingFlx> findByClientFlx(ClientFlx clientFlx);

//   @Query("SELECT i FROM TrackingFlx i where i.flxClient.id = :flxClientId")
//   Iterable<TrackingFlx> findTrackingByFlxClientId(Long flxClientId);

}

