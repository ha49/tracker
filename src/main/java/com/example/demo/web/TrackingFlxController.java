package com.example.demo.web;


import com.example.demo.entity.ClientFlx;
import com.example.demo.entity.TrackingFlx;
import com.example.demo.repository.ClientFlxRepository;
import com.example.demo.repository.TrackingFlxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/tracking")
public class TrackingFlxController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrackingFlxController.class);
    private TrackingFlxRepository trackingFlxRepository;
    private ClientFlxRepository clientFlxRepository;

    public TrackingFlxController(TrackingFlxRepository trackingFlxRepository,
                                 ClientFlxRepository clientFlxRepository) {
        this.trackingFlxRepository = trackingFlxRepository;
        this.clientFlxRepository = clientFlxRepository;
    }

    @GetMapping("/getall")
    public Iterable<TrackingFlx> getAllTrackings(){
        LOGGER.info("tracking/getall ☺");
        return trackingFlxRepository.findAll();
    }

    @GetMapping("/getforclient/{clientId}")
    public Iterable<TrackingFlx> getAllTrackings4Client(@PathVariable long clientId){
        LOGGER.info("tracking/getforclient/");


        ClientFlx clientFlx = clientFlxRepository.findById(clientId).orElseThrow(()->
                new NoSuchElementException("Coach with id "+ clientId+ " does not exist " ));

        Iterable<TrackingFlx> tracking= trackingFlxRepository.findByClientFlx(clientFlx);
        return tracking;


    }

    // ADD NEW
    @PostMapping("/new")
    public TrackingFlx addNewTracking(@RequestBody TrackingFlx trackingFlx){

        clientFlxRepository.findById(trackingFlx.getClientFlx().getId()).orElseThrow(()->
                new NoSuchElementException( "Client with id "+ trackingFlx.getClientFlx().getId()+ " does not exist "  ));

        LOGGER.info("tracking/new/ ☺");
        return trackingFlxRepository.save(trackingFlx);
    }

    @DeleteMapping("/delete/{id}")

    public void deleteTracking(@PathVariable long id){
        TrackingFlx foundTracking=verifyTracking(id);
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
