package com.example.demo.auth;


import com.example.demo.entity.CoachFlx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
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
    //    @PreAuthorize("hasRole('ADMIN')")
    public UserFlx createCoach(@RequestBody CoachFlx coachFlx) {
        //           logger.trace("Vi loggar på TRACE-nivå");
        //           logger.debug("Vi loggar på DEBUG-nivå");
        logger.info("createCoach() was called with username: " + coachFlx.getUserFlx().getUsername());
        //           logger.warn("Vi loggar på WARN-nivå");
        //           logger.error("Vi loggar på ERROR-nivå");
        return userFlxService.createCoach(coachFlx);
    }


    @PostMapping("/createAdmin")
    //    @PreAuthorize("hasRole('ADMIN')")
    public UserFlx createAdmin(@RequestBody UserFlx userFlx) {
        //           logger.trace("Vi loggar på TRACE-nivå");
        //           logger.debug("Vi loggar på DEBUG-nivå");
        logger.info("createAdmin() was called with username: " + userFlx.getUsername());
        //           logger.warn("Vi loggar på WARN-nivå");
        //           logger.error("Vi loggar på ERROR-nivå");
        return userFlxService.createAdmin(userFlx);
    }



    @GetMapping("/findall")
//    @PreAuthorize("hasRole('ADMIN')")
    public Iterable<UserFlx> findAllUsers() {
        return userFlxService.findAllUsers();
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public Optional<UserFlx> findUserById(@PathVariable Long id) {
        return userFlxService.findUserById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userFlxService.deleteUser(id);
    }

    @GetMapping("/getauthenticateduser")
    public UserFlx getAuthenticatedUser() {
        return userFlxService.getAuthenticatedUser();
    }
}
