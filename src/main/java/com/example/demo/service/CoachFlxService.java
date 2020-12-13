package com.example.demo.service;


import com.example.demo.entity.CoachFlx;
import com.example.demo.repository.CoachFlxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoachFlxService {

    private CoachFlxRepository coachFlxRepository;

    @Autowired

    public CoachFlxService (CoachFlxRepository coachFlxRepository){
        this.coachFlxRepository=coachFlxRepository;
    }


    public CoachFlx addCoach(CoachFlx coachFlx){

        return coachFlxRepository.save(coachFlx);
    }


    public Iterable<CoachFlx>   getAllCoaches(){
        return coachFlxRepository.findAll();
    }


    public void deleteCoach (long id){

        Optional<CoachFlx> foundCoach=coachFlxRepository.findById(id);
        coachFlxRepository.deleteById(foundCoach.get().getId());
    }


    public Optional<CoachFlx> findById(long id) {
        Optional<CoachFlx> foundCoach=coachFlxRepository.findById(id);
        return foundCoach;


    }
}
