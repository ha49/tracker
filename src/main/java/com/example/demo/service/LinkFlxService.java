package com.example.demo.service;

import com.example.demo.entity.CoachFlx;
import com.example.demo.entity.LinkFlx;
import com.example.demo.repository.CoachFlxRepository;
import com.example.demo.repository.LinkFlxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LinkFlxService {
    private LinkFlxRepository linkFlxRepository;
    private CoachFlxRepository coachFlxRepository;

    @Autowired
    public LinkFlxService(LinkFlxRepository linkFlxRepository, CoachFlxRepository coachFlxRepository){
        this.linkFlxRepository = linkFlxRepository;
        this.coachFlxRepository = coachFlxRepository;
    }

    public LinkFlx addLink(LinkFlx linkFlx){
        CoachFlx coachFlx = coachFlxRepository.findById(linkFlx.getCoachFlx().getId()).orElseThrow(()->
                new RuntimeException("Coach does not exist: " + linkFlx.getCoachFlx().getId()));

        return linkFlxRepository.save(linkFlx);
    }

    public Iterable<LinkFlx> getAllLinks(){

        return linkFlxRepository.findAll();
    }

    public void deleteLink (long id){
        Optional<LinkFlx> foundLink = linkFlxRepository.findById(id);
        linkFlxRepository.deleteById(foundLink.get().getId());
    }

    public Iterable<LinkFlx> getAllLinks4Coach(long coachId){

        CoachFlx coachFlx = coachFlxRepository.findById(coachId).orElseThrow(()->
                new RuntimeException("Coach does not exist: " + coachId));

        Iterable<LinkFlx> links = linkFlxRepository.findByCoachFlx(coachFlx);
        return links;
    }

    public Optional<LinkFlx> findById(long id) {
        Optional<LinkFlx> foundLinks = linkFlxRepository.findById(id);
        return foundLinks;
    }
}
