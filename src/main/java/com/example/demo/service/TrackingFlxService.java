package com.example.demo.service;

import com.example.demo.entity.ClientFlx;
import com.example.demo.entity.TrackingFlx;
import com.example.demo.repository.ClientFlxRepository;
import com.example.demo.repository.TrackingFlxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TrackingFlxService {

    private TrackingFlxRepository trackingFlxRepository;
    private ClientFlxRepository clientFlxRepository;


    @Autowired
    public TrackingFlxService(TrackingFlxRepository trackingFlxRepository, ClientFlxRepository clientFlxRepository){
        this.trackingFlxRepository = trackingFlxRepository;
        this.clientFlxRepository = clientFlxRepository;
    }

    public TrackingFlx addTracking(TrackingFlx trackingFlx){
        ClientFlx clientFlx = clientFlxRepository.findById(trackingFlx.getClientFlx().getId()).orElseThrow(()->
                new RuntimeException("Client does not exist: " + trackingFlx.getClientFlx().getId()));

        return trackingFlxRepository.save(trackingFlx);
    }

    public Iterable<TrackingFlx> getAllTrackings(){

        return trackingFlxRepository.findAll();
    }

    public void deleteTracking (long id){
        Optional<TrackingFlx> foundTracking = Optional.ofNullable(trackingFlxRepository.findById(id));
        trackingFlxRepository.deleteById(foundTracking.get().getId());
    }

    public Iterable<TrackingFlx> getAllTracking4Client(long clientId){

        ClientFlx clientFlx = clientFlxRepository.findById(clientId).orElseThrow(()->
                new RuntimeException("Client does not exist: " + clientId));

        Iterable<TrackingFlx> trackings = trackingFlxRepository.findByClientFlx(clientFlx);
        return trackings;
    }

    public Optional<TrackingFlx> findById(long id) {
        Optional<TrackingFlx> foundTracking = Optional.ofNullable(trackingFlxRepository.findById(id));
        return foundTracking;
    }
}

