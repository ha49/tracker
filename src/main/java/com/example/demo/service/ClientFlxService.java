package com.example.demo.service;

import com.example.demo.entity.ClientFlx;
import com.example.demo.entity.CoachFlx;
import com.example.demo.repository.ClientFlxRepository;
import com.example.demo.repository.CoachFlxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientFlxService {

    private ClientFlxRepository clientFlxRepository;
    private CoachFlxRepository coachFlxRepository;

    @Autowired
    public ClientFlxService(ClientFlxRepository clientFlxRepository, CoachFlxRepository coachFlxRepository){

        this.clientFlxRepository = clientFlxRepository;
        this.coachFlxRepository = coachFlxRepository;
    }


    public ClientFlx addClient(ClientFlx clientFlx){
        CoachFlx coachFlx = coachFlxRepository.findById(clientFlx.getCoachFlx().getId()).orElseThrow(()->
                new RuntimeException("Coach does not exist: " + clientFlx.getCoachFlx().getId()));

        return clientFlxRepository.save(clientFlx);
    }


    public Iterable<ClientFlx>   getAllClients(){

        return clientFlxRepository.findAll();
    }


    public void deleteClient (long id){

        Optional<ClientFlx> foundClient=clientFlxRepository.findById(id);
        clientFlxRepository.deleteById(foundClient.get().getId());
    }


    public Optional<ClientFlx> findById(long id) {
        Optional<ClientFlx> foundClient=clientFlxRepository.findById(id);
        return foundClient;
    }

    public Iterable<ClientFlx> getAllClients4Coach(long coachId){

        CoachFlx coachFlx = coachFlxRepository.findById(coachId).orElseThrow(()->
                new RuntimeException("Coach does not exist: " + coachId));

        Iterable<ClientFlx> clients = clientFlxRepository.findByCoachFlx(coachFlx);
        return clients;
    }
}
