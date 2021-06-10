package com.example.tracker.web;

import com.example.tracker.entity.ClientFlx;
import com.example.tracker.repository.ClientFlxRepository;
import com.example.tracker.repository.CoachFlxRepository;
import com.example.tracker.repository.DocumentFlxRepository;
import com.example.tracker.repository.UserFlxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/client")
@CrossOrigin("http://localhost:3000")
public class ClientFlxController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientFlxController.class);
    private ClientFlxRepository clientFlxRepository;
    private CoachFlxRepository coachFlxRepository;
    private UserFlxRepository userFlxRepository;
    private DocumentFlxRepository documentFlxRepository;

    public ClientFlxController(ClientFlxRepository clientFlxRepository,
                               CoachFlxRepository coachFlxRepository,
                               UserFlxRepository userFlxRepository,
                               DocumentFlxRepository documentFlxRepository
    ) {
        this.clientFlxRepository = clientFlxRepository;
        this.coachFlxRepository = coachFlxRepository;
        this.userFlxRepository = userFlxRepository;
        this.documentFlxRepository = documentFlxRepository;
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
//    @PreAuthorize("hasRole('coach')")
    public Iterable<ClientFlx> getAllClients() {
        LOGGER.info("client/getall ☺");
        return clientFlxRepository.findAll();
    }

/*
    // ADD NEW CLIENT MOVED TO USER CONTROLLER
    @PostMapping("/new")
    public ClientFlx addNewClient(@RequestBody ClientFlx clientFlx) {
        //        CoachFlx coachFlx = coachFlxRepository.findById(clientFlx.getCoachFlx().getId()).orElseThrow(()->
        //        new RuntimeException("Coach does not exist: " + clientFlx.getCoachFlx().getId()));

        LOGGER.info("client/new/ ☺");
        return clientFlxRepository.save(clientFlx);
    }
*/


    // DELETE CLIENT BY ID
    @DeleteMapping("/delete/{id}")
    //    @PreAuthorize("hasRole('client')")

    public void deleteClient(@PathVariable long id) {

        LOGGER.info("client/delete/" + id + " ☺");

        ClientFlx foundClient = verifyClient(id);
        System.out.println("fount client id " + foundClient.getId());

        //        for (DocumentFlx document : foundClient.getDocuments()
        //        ) {
        //            System.out.println("document id: " + document.getId());
        //            documentFlxRepository.delete(document);
        //        }

        clientFlxRepository.deleteById(id);
        //        userFlxRepository.delete(foundClient.getUserFlx());
        System.out.println(clientFlxRepository.getClientFlxById(foundClient.getId()));
    }


    // VERIFY IF EXISTS
    private ClientFlx verifyClient(long id) throws NoSuchElementException {
        return clientFlxRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Client with id " + id + " does not exist "));
    }

    @PostMapping(
            path ="/{id}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
//    public void uploadClientPhoto(@PathVariable long id,
//                                  @RequestParam("file") MultipartFile file){
//
//        // 1. Check if image is not empty
//        isFileEmpty(file);
//        // 2. If file is an image
//        isImage(file);
//
//        // 3. The user exists in our database
//        UserProfile user = getUserProfileOrThrow(userProfileId);
//
//        // 4. Grab some metadata from file if any
//        Map<String, String> metadata = extractMetadata(file);
//
//        // 5. Store the image in s3 and update database (userProfileImageLink) with s3 image link
//        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
//        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
//
//        try {
//            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
//            user.setUserProfileImageLink(filename);
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
//        clientFlxRepository.uploadClientPhoto(id, file);
//    }
//
//    private void isImage(MultipartFile file) {
//        if (!Arrays.asList(
//                IMAGE_JPEG.getMimeType(),
//                IMAGE_PNG.getMimeType(),
//                IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
//            throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");
//        }
//    }

    private void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + "]");
        }
    }
    // NO SUCH ELEMENT EXCEPTION
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();
    }


}