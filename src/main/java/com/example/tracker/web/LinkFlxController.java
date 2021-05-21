package com.example.tracker.web;


import com.example.tracker.entity.CoachFlx;
import com.example.tracker.entity.LinkFlx;
import com.example.tracker.repository.CoachFlxRepository;
import com.example.tracker.repository.LinkFlxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/link")

public class LinkFlxController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkFlxController.class);
    private LinkFlxRepository linkFlxRepository;
    private CoachFlxRepository coachFlxRepository;

    public LinkFlxController(LinkFlxRepository linkFlxRepository,
                             CoachFlxRepository coachFlxRepository) {
        this.linkFlxRepository = linkFlxRepository;
        this.coachFlxRepository = coachFlxRepository;
    }

    //    GET ONE

    @GetMapping("/get/{id}")
    public LinkFlx getLink(@PathVariable long id) {
        LOGGER.info("link/get/" + id + " ☺");

        return linkFlxRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Link with id " + id + " does not exist ")
        );
    }


    @GetMapping("/getall")
    public Iterable<LinkFlx> getAllLinks() {
        LOGGER.info("link/getall ☺");
        return linkFlxRepository.findAll();
    }

    @GetMapping("/getforcoach/{coachId}")
    public Iterable<LinkFlx> getAllLinks4Coach(@PathVariable long coachId) {
        LOGGER.info("link/getforcoach/" + coachId + " ☺");


        CoachFlx coachFlx = coachFlxRepository.findById(coachId).orElseThrow(() ->
                new RuntimeException("Coach with id " + coachId + " does not exist "));

        Iterable<LinkFlx> links = linkFlxRepository.findByCoachFlx(coachFlx);
        return links;
    }


    @PostMapping("/new")
    public LinkFlx addNewLink(@RequestBody LinkFlx linkFlx) {
        LOGGER.info("link/new/ ☺");

        return linkFlxRepository.save(linkFlx);
    }


    @DeleteMapping("/delete/{id}")

    public void deleteLink(@PathVariable long id) {
        LOGGER.info("link/delete/" + id + " ☺");
        LinkFlx foundLink = verifyLink(id);
        linkFlxRepository.delete(foundLink);

    }

    private LinkFlx verifyLink(long id) throws NoSuchElementException {
        return linkFlxRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Link with id " + id + " does not exist "));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();
    }
}
