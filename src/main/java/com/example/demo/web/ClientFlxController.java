package com.example.demo.web;

import com.example.demo.entity.ClientFlx;
import com.example.demo.entity.CoachFlx;
import com.example.demo.repository.ClientFlxRepository;
import com.example.demo.repository.CoachFlxRepository;
import com.example.demo.service.ClientFlxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/client")
public class ClientFlxController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientFlxController.class);
    private ClientFlxService clientFlxService;
    private ClientFlxRepository clientFlxRepository;
    private CoachFlxRepository coachFlxRepository;

    public ClientFlxController(ClientFlxService clientFlxService, ClientFlxRepository clientFlxRepository, CoachFlxRepository coachFlxRepository) {
        this.clientFlxService = clientFlxService;
        this.clientFlxRepository = clientFlxRepository;
        this.coachFlxRepository = coachFlxRepository;
    }

    @GetMapping("/get/{id}")
    public ClientFlx getClient(@PathVariable long id){
        LOGGER.info("client/get {id} ☺");
        return clientFlxRepository.findById(id).get();
    }

    @GetMapping("/getall")
    public Iterable<ClientFlx> getAllClient(){
        LOGGER.info("client/getall ☺");
        return clientFlxRepository.findAll();
    }

    @GetMapping("/getforcoach/{coachId}")
    public Iterable<ClientFlx> getAllClient4Coach(@PathVariable long coachId){
        LOGGER.info("client/getforcoach/");

        CoachFlx coachFlx = coachFlxRepository.findById(coachId).orElseThrow(()->
                new RuntimeException("Coach does not exist: " + coachId));

        Iterable<ClientFlx> clients = clientFlxRepository.findByCoachFlx(coachFlx);
        return clients;

    }

    @PostMapping("/new")
    public ClientFlx addNewClient(@RequestBody ClientFlx clientFlx){

        return clientFlxService.addClient(clientFlx);
    }

    @DeleteMapping("/delete/{id}")

    public void deleteClient(@PathVariable long id){

        //        Optional<ClientFlx> foundClient=clientFlxRepository.findById(id);
        //        clientFlxRepository.deleteById(foundClient.get().getId());

        ClientFlx foundClient=verifyClient(id);
        clientFlxRepository.deleteById(foundClient.getId());
    }

    private ClientFlx verifyClient(long id) throws NoSuchElementException {
        return clientFlxRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Client does not exist " + id));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();
    }
}
