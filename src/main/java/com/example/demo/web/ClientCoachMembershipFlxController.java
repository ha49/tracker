package com.example.demo.web;


import com.example.demo.entity.ClientCoachMembershipFlx;
import com.example.demo.entity.ClientFlx;
import com.example.demo.entity.CoachFlx;
import com.example.demo.repository.ClientCoachMembershipFlxRepository;
import com.example.demo.repository.ClientFlxRepository;
import com.example.demo.repository.CoachFlxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/member")
public class ClientCoachMembershipFlxController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkFlxController.class);

    private ClientCoachMembershipFlxRepository membershipRepository;
    private ClientFlxRepository clientFlxRepository;
    private CoachFlxRepository coachFlxRepository;

    public ClientCoachMembershipFlxController(ClientCoachMembershipFlxRepository membershipRepository,
                                              ClientFlxRepository clientFlxRepository,
                                              CoachFlxRepository coachFlxRepository) {
        this.membershipRepository = membershipRepository;
        this.clientFlxRepository = clientFlxRepository;
        this.coachFlxRepository = coachFlxRepository;
    }

    // ADD NEW
    @PostMapping("/new")
    public ClientCoachMembershipFlx addNewMembership(@RequestBody ClientCoachMembershipFlx membershipFlx){

        CoachFlx coachFlx = coachFlxRepository.findById(membershipFlx.getCoachFlx().getId()).orElseThrow(()->
                new RuntimeException("Coach does not exist: " + membershipFlx.getCoachFlx().getId()));

        ClientFlx clientFlx = clientFlxRepository.findById(membershipFlx.getClientFlx().getId()).orElseThrow(()->
                new RuntimeException("Client does not exist: " + membershipFlx.getClientFlx().getId()));

        LOGGER.info("clientcoachmembership/new/ ☺");
        return membershipRepository.save(membershipFlx);

    }

    // GET ALL
    @GetMapping("/getall")
    public Iterable<ClientCoachMembershipFlx> getAllMemberships(){
        LOGGER.info("clientcoachmembership/getall ☺");
        return membershipRepository.findAll();
    }

    // GET FOR CLIENT
    @GetMapping("/getforclient/{clientId}")
    public Iterable<ClientCoachMembershipFlx> getMemberships4Client(@PathVariable long clientId){
        LOGGER.info("link/getforclient/" + clientId + " ☺");

        ClientFlx clientFlx = clientFlxRepository.findById(clientId).orElseThrow(()->
                new RuntimeException("Client with id "+ clientId + " does not exist "));

        Iterable<ClientCoachMembershipFlx> memberships = membershipRepository.findByClientFlx(clientFlx);
        return memberships;
    }

    // GET FOR COACH
    @GetMapping("/getforcoach/{coachId}")
    public Iterable<ClientCoachMembershipFlx> getMemberships4Coach(@PathVariable long coachId){
        LOGGER.info("link/getforcoach/" + coachId + " ☺");

        CoachFlx coachFlx = coachFlxRepository.findById(coachId).orElseThrow(()->
                new RuntimeException("Coach with id "+ coachId+ " does not exist "));

        Iterable<ClientCoachMembershipFlx> memberships = membershipRepository.findByCoachFlx(coachFlx);
        return memberships;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMembership(@PathVariable long id){
        LOGGER.info("clientcoachmembership/delete/"+id + " ☺");
        ClientCoachMembershipFlx foundMembership=verifyData(id);
        membershipRepository.delete(foundMembership);
    }

    private ClientCoachMembershipFlx verifyData(long id) throws NoSuchElementException {
        return membershipRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("ClientCoachMembership with id "+ id+ " does not exist "));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();
    }


}
