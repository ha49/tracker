package com.example.demo.web;


import com.example.demo.entity.CoachFlx;
import com.example.demo.service.CoachFlxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/coach")
public class CoachFlxController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientFlxController.class);
    private CoachFlxService coachFlxService;
    CoachFlxService coachFlxRepository;


    public CoachFlxController(CoachFlxService coachFlxService, CoachFlxService coachFlxRepository) {
        this.coachFlxService = coachFlxService;
        this.coachFlxRepository = coachFlxRepository;
    }

    @GetMapping("/get/{id}")
    public CoachFlx getClient(@PathVariable long id){
        LOGGER.info("coach/get {id} ☺");
        return coachFlxRepository.findById(id).get();
    }

    @GetMapping("/getall")
    public Iterable<CoachFlx> getAllCoaches(){
        return  coachFlxService.getAllCoaches();

    }

    @PostMapping("/new")
    public  CoachFlx addNewCoach(@RequestBody CoachFlx coachFlx){

        return coachFlxService.addCoach(coachFlx);
    }

    @DeleteMapping("/delete/{id}")

    public void deleteCoach(@PathVariable long id){
        CoachFlx foundCoach=verifyCoach(id);
        coachFlxService.deleteCoach(foundCoach.getId());
    }

    private CoachFlx verifyCoach(long id) throws NoSuchElementException {
        return coachFlxService.findById(id).orElseThrow(() ->
                new NoSuchElementException("Coach does not exist " + id));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();
    }


}
