package com.example.tracker.web;


import com.example.tracker.entity.ClientFlx;
import com.example.tracker.entity.DocumentFlx;
import com.example.tracker.repository.ClientFlxRepository;
import com.example.tracker.repository.DocumentFlxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1/document")
public class DocumentFlxController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentFlxController.class);
    private DocumentFlxRepository documentFlxRepository;
    private ClientFlxRepository clientFlxRepository;


    public DocumentFlxController(DocumentFlxRepository documentFlxRepository,
                                 ClientFlxRepository clientFlxRepository) {
        this.documentFlxRepository = documentFlxRepository;
        this.clientFlxRepository = clientFlxRepository;
    }


    //    GET ONE

    @GetMapping("/get/{id}")
    public DocumentFlx getDocument(@PathVariable long id) {
        LOGGER.info("document/get/" + id + " ☺");

        return documentFlxRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Client with id " + id + " does not exist ")
        );
    }

    //    GET ALL
    @GetMapping("/getall")
    public Iterable<DocumentFlx> getAllDocuments() {
        LOGGER.info("document/getall ☺");
        return documentFlxRepository.findAll();
    }

    // GET DOCUMENTS OF A CLIENT
    @GetMapping("/getforclient/{clientId}")
    public Iterable<DocumentFlx> getAllDocuments4Client(@PathVariable long clientId) {
        LOGGER.info("document/getforcoach/" + clientId + " ☺");

        ClientFlx clientFlx = clientFlxRepository.findById(clientId).orElseThrow(() ->
                new NoSuchElementException("Client with id " + clientId + " does not exist "));

        Iterable<DocumentFlx> documents = documentFlxRepository.findByClientFlx(clientFlx);

        return documents;


    }

    // ADD NEW

    @PostMapping("/new")
    public DocumentFlx addNewDocument(@RequestBody DocumentFlx documentFlx) {


        clientFlxRepository.findById(documentFlx.getClientFlx().getId()).orElseThrow(() ->
                new NoSuchElementException("Client with id " + documentFlx.getClientFlx().getId() + " does not exist "));

        LOGGER.info("document/new/ ☺");

        return documentFlxRepository.save(documentFlx);
    }


    // DELETE ONE
    @DeleteMapping("/delete/{id}")

    public void deleteDocument(@PathVariable long id) {
        DocumentFlx foundDocument = verifyDocument(id);
        documentFlxRepository.delete(foundDocument);
    }

    private DocumentFlx verifyDocument(long id) throws NoSuchElementException {
        return documentFlxRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Document does not exist " + id));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();
    }
}




