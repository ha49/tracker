package com.example.tracker.web;


import com.example.tracker.entity.ClientCoachMembershipFlx;
import com.example.tracker.entity.TrackingFlx;
import com.example.tracker.repository.ClientCoachMembershipFlxRepository;
import com.example.tracker.repository.TrackingFlxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/tracking")
public class TrackingFlxController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrackingFlxController.class);
    private TrackingFlxRepository trackingFlxRepository;
    private ClientCoachMembershipFlxRepository membershipRepository;
    //private ClientFlxRepository clientFlxRepository;
    //private CoachFlxRepository coachFlxRepository;

    public TrackingFlxController(TrackingFlxRepository trackingFlxRepository,
                                 ClientCoachMembershipFlxRepository membershipRepository) {
        this.trackingFlxRepository = trackingFlxRepository;
        this.membershipRepository = membershipRepository;
    }

    @GetMapping("/getall")
    public Iterable<TrackingFlx> getAllTrackings() {
        LOGGER.info("tracking/getall ☺");
        return trackingFlxRepository.findAll();
    }

    @GetMapping("/getbydate/{date}")
    public Iterable<TrackingFlx> getTrackingByDate(@PathVariable Date date) {
        LOGGER.info("tracking/getbydate ☺");
        Iterable<TrackingFlx> trackings = trackingFlxRepository.findByTrackingDate(date);
        return trackings;
    }

    //GET FOR MEMBERSHIP
    @GetMapping("/getformembership/{membershipId}")
    public Iterable<TrackingFlx> getAllTrackings4Membership(@PathVariable long membershipId) {
        LOGGER.info("tracking/getformembership/");

        ClientCoachMembershipFlx clientCoachMembershipFlx = membershipRepository.findById(membershipId).orElseThrow(() ->
                new NoSuchElementException("Coach with id " + membershipId + " does not exist "));

        Iterable<TrackingFlx> tracking = trackingFlxRepository.findByClientCoachMembershipFlx(membershipId);
        return tracking;


    }

    // ADD NEW
    @PostMapping("/new")
    public TrackingFlx addNewTracking(@RequestBody TrackingFlx trackingFlx) {

        membershipRepository.findById(trackingFlx.getClientCoachMembershipFlx().getId()).orElseThrow(() ->
                new NoSuchElementException("Membership with id " + trackingFlx.getClientCoachMembershipFlx().getId() + " does not exist "));

        LOGGER.info("tracking/new/ ☺");
        return trackingFlxRepository.save(trackingFlx);
    }

    @PutMapping("/update/{id}")
    public void updateTracking(@RequestBody TrackingFlx trackingFlx, @PathVariable Long id) {
        TrackingFlx foundTracking = verifyTracking(id);

        foundTracking.setClientNote(trackingFlx.getClientNote());
        foundTracking.setCoachNote(trackingFlx.getCoachNote());
        foundTracking.setDietRate(trackingFlx.getDietRate());
        foundTracking.setExerciseRate(trackingFlx.getExerciseRate());
        foundTracking.setMode(trackingFlx.getMode());
        trackingFlxRepository.save(foundTracking);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTracking(@PathVariable long id) {
        TrackingFlx foundTracking = verifyTracking(id);
        trackingFlxRepository.delete(foundTracking);
    }

    // VERIFY IF EXISTS
    private TrackingFlx verifyTracking(long id) throws NoSuchElementException {
        return trackingFlxRepository.findById(id);
        //
        //                .orElseThrow(() ->
        //                new NoSuchElementException("Tracking does not exist " + id));
    }

    // NO SUCH ELEMENT EXCEPTION
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();
    }
}
