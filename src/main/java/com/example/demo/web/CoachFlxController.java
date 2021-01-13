package com.example.demo.web;


import com.example.demo.entity.ClientFlx;
import com.example.demo.entity.CoachFlx;
import com.example.demo.repository.ClientCoachMembershipFlxRepository;
import com.example.demo.repository.CoachFlxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/coach")
public class CoachFlxController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoachFlxController.class);

    private CoachFlxRepository coachFlxRepository;
    private ClientCoachMembershipFlxRepository membershipFlxRepository;

    public CoachFlxController(CoachFlxRepository coachFlxRepository,
                              ClientCoachMembershipFlxRepository membershipFlxRepository) {
        this.coachFlxRepository = coachFlxRepository;
        this.membershipFlxRepository = membershipFlxRepository;
    }



    //    GET ONE
    @GetMapping("/get/{id}")
    public CoachFlx getCoach(@PathVariable long id){
        LOGGER.info("coach/get/"+id + " ☺");

        return coachFlxRepository.findById(id).orElseThrow(
                ()-> new NoSuchElementException("Coach with id "+ id+ " does not exist ")
        );
    }

    // GET CLIENTS FOR COACH
    @GetMapping("/getclients/{coachId}")
    public Iterable<ClientFlx> getClients(@PathVariable long coachId){
        LOGGER.info("coach/getclients/"+coachId + " ☺");

        CoachFlx coachFlx = coachFlxRepository.findById(coachId).orElseThrow(()->
                new NoSuchElementException("Coach with id "+ coachId+ " does not exist " ));

       /* Iterable<ClientCoachMembershipFlx> memberships = membershipFlxRepository.findByCoachFlx(coachFlx);

        return memberships;*/

        Iterable<ClientFlx> clients = membershipFlxRepository.findClientFlxByStatusAndCoachIdParams("Active",coachId);
        return clients;

    }

    //    GET ALL
    @GetMapping("/getall")
    public Iterable<CoachFlx> getAllCoaches(){
        LOGGER.info("coach/getall ☺");

        return  coachFlxRepository.findAll();


    }



    // ADD NEW
    @PostMapping("/new")
    public  CoachFlx addNewCoach(@RequestBody CoachFlx coachFlx){
        LOGGER.info("coach/new/{id}" + " ☺");
        return coachFlxRepository.save(coachFlx);
    }

    // DELETE ONE
    @DeleteMapping("/delete/{id}")
    public void deleteCoach(@PathVariable long id){
        LOGGER.info("client/delete/"+id + " ☺");
        CoachFlx foundCoach=verifyCoach(id);
        coachFlxRepository.delete(foundCoach);
    }

    private CoachFlx verifyCoach(long id) throws NoSuchElementException {
        return coachFlxRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Coach does not exist " + id));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();
    }


}
