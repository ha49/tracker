package com.example.tracker.web;

import com.example.tracker.auth.AuthGroup;
import com.example.tracker.auth.UserFlxPrincipal;
import com.example.tracker.entity.ClientFlx;
import com.example.tracker.repository.ClientFlxRepository;
import com.example.tracker.repository.CoachFlxRepository;
import com.example.tracker.repository.UserFlxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/client")
public class ClientFlxController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientFlxController.class);
    private ClientFlxRepository clientFlxRepository;
    private CoachFlxRepository coachFlxRepository;
    private UserFlxRepository userFlxRepository;

    private UserFlxPrincipal userFlxPrincipal;

    public ClientFlxController(ClientFlxRepository clientFlxRepository,
                               CoachFlxRepository coachFlxRepository,
                               UserFlxRepository userFlxRepository
    ) {
        this.clientFlxRepository = clientFlxRepository;
        this.coachFlxRepository = coachFlxRepository;
        this.userFlxRepository=userFlxRepository;

    }

    //    GET ONE

    @GetMapping("/get/{id}")


    public ClientFlx getClient(@PathVariable long id) {
        LOGGER.info("client/get/" + id + " ☺");
        return clientFlxRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Client with id " + id + " does not exist ")
        );
    }



    //    GET ALL
    @GetMapping("/getall")
    @PreAuthorize("hasRole('coach')")
    public Iterable<ClientFlx> getAllClients() {
        LOGGER.info("client/getall ☺");
        return clientFlxRepository.findAll();
    }



    // ADD NEW
    @PostMapping("/new")
    public ClientFlx addNewClient(@RequestBody ClientFlx clientFlx) {

        //        CoachFlx coachFlx = coachFlxRepository.findById(clientFlx.getCoachFlx().getId()).orElseThrow(()->
        //                new RuntimeException("Coach does not exist: " + clientFlx.getCoachFlx().getId()));

        LOGGER.info("client/new/ ☺");
        return clientFlxRepository.save(clientFlx);

    }


    // DELETE ONE
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('client')")

    public void deleteClient(@PathVariable long id) {
        LOGGER.info("coach/delete/" + id + " ☺");

        ClientFlx foundClient = verifyClient(id);
        clientFlxRepository.delete(foundClient);
    }


    // VERIFY IF EXISTS
    private ClientFlx verifyClient(long id) throws NoSuchElementException {
        return clientFlxRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Client with id " + id + " does not exist "));
    }

    // NO SUCH ELEMENT EXCEPTION
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();
    }
}