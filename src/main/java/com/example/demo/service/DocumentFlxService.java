package com.example.demo.service;

import com.example.demo.entity.ClientFlx;
import com.example.demo.entity.DocumentFlx;
import com.example.demo.repository.ClientFlxRepository;
import com.example.demo.repository.DocumentFlxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DocumentFlxService {

    private DocumentFlxRepository documentFlxRepository;
    private ClientFlxRepository clientFlxRepository;

    @Autowired
    public DocumentFlxService(DocumentFlxRepository documentFlxRepository, ClientFlxRepository clientFlxRepository){
        this.documentFlxRepository = documentFlxRepository;
        this.clientFlxRepository = clientFlxRepository;
    }

    public DocumentFlx addDocument(DocumentFlx documentsFlx){
        ClientFlx clientFlx = clientFlxRepository.findById(documentsFlx.getClientFlx().getId()).orElseThrow(()->
                new RuntimeException("Client does not exist: " + documentsFlx.getClientFlx().getId()));

        return documentFlxRepository.save(documentsFlx);
    }

    public Iterable<DocumentFlx> getAllDocuments(){

        return documentFlxRepository.findAll();
    }

    public void deleteDocument (long id){
        Optional<DocumentFlx> foundDocument = documentFlxRepository.findById(id);
        documentFlxRepository.deleteById(foundDocument.get().getId());
    }

    public Iterable<DocumentFlx> getAllDocument4Client(long clientId){

        ClientFlx clientFlx = clientFlxRepository.findById(clientId).orElseThrow(()->
                new RuntimeException("Client does not exist: " + clientId));

        Iterable<DocumentFlx> documents = documentFlxRepository.findByClientFlx(clientFlx);

        return documents;
    }

    public Optional<DocumentFlx> findById(long id) {
        Optional<DocumentFlx> foundDocument = documentFlxRepository.findById(id);
        return foundDocument;
    }
}



