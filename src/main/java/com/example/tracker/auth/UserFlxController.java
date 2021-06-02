package com.example.tracker.auth;


import com.example.tracker.entity.ClientFlx;
import com.example.tracker.entity.CoachFlx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
//@PreAuthorize("isAuthenticated()")
public class UserFlxController {



    Logger logger = LoggerFactory.getLogger(UserFlxController.class);

    private UserFlxService userFlxService;

    public UserFlxController(UserFlxService userFlxService) {
        this.userFlxService = userFlxService;
    }

    @PostMapping("/createUser")
    //    @PreAuthorize("hasRole('COACH')")
    public UserFlx createUser(@RequestBody UserFlx userFlx) {
        //           logger.trace("Vi loggar på TRACE-nivå");
        //           logger.debug("Vi loggar på DEBUG-nivå");
        logger.info("createUser() was called with username: " + userFlx.getUsername());
        //           logger.warn("Vi loggar på WARN-nivå");
        //           logger.error("Vi loggar på ERROR-nivå");
        return userFlxService.createUser(userFlx);
    }

    @PostMapping("/createCoach")

    public UserFlx createCoach(@RequestBody CoachFlx coachFlx) {

        logger.info("createCoach() was called with username: " + coachFlx.getUserFlx().getUsername());



        return userFlxService.createCoach(coachFlx);
    }

    @PostMapping("/createClient")

    public ResponseEntity<UserFlx> createClient(@RequestBody ClientFlx clientFlx) {
        logger.info("createClient() was called with username: " + clientFlx.getUserFlx().getUsername());
        return userFlxService.createClient(clientFlx);
    }




    @PostMapping("/createAdmin")
    //    @PreAuthorize("hasRole('ADMIN')")
    public UserFlx createAdmin(@RequestBody UserFlx userFlx) {

        logger.info("createAdmin() was called with username: " + userFlx.getUsername());

        return userFlxService.createAdmin(userFlx);
    }


    @GetMapping("/findall")
    public Iterable<UserFlx> findAllUsers() {
        return userFlxService.findAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public Optional<UserFlx> findUserById(@PathVariable Long id) {
        return userFlxService.findUserById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userFlxService.deleteUser(id);
    }
    @DeleteMapping("/delete/client/{id}")
    public void deleteClient(@PathVariable Long id) {

        userFlxService.deleteClientFlx(id);
    }
//
// //This method is problematic
//    @DeleteMapping("/delete/")
//    public void deleteClientFlx(@PathVariable String username) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String username = auth.getName();
//        userFlxService.deleteClientFlx(username);
//    }

    @GetMapping("/getauthenticateduser")
    public ClientFlx getAuthenticatedUser() {
        return userFlxService.getAuthenticatedUser();
    }
}
