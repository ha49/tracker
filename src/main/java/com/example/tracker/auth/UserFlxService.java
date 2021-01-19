package com.example.tracker.auth;


import com.example.tracker.entity.ClientFlx;
import com.example.tracker.entity.CoachFlx;
import com.example.tracker.exceptions.UserNameAlreadyTakenException;
import com.example.tracker.jms.sender.MessagePublisher;
import com.example.tracker.repository.ClientFlxRepository;
import com.example.tracker.repository.CoachFlxRepository;
import com.example.tracker.repository.UserFlxRepository;
import com.example.tracker.web.CoachFlxController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserFlxService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserFlxService.class);

    @Autowired
    AuthGroupRepository authGroupRepository;

    @Autowired
    CoachFlxRepository coachFlxRepository;

    @Autowired
    ClientFlxRepository clientFlxRepository;

    @Autowired
    MessagePublisher messagePublisher;

    private UserFlxRepository userFlxRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Constructor injection
    public UserFlxService(UserFlxRepository userFlxRepository,
                          CoachFlxRepository coachFlxRepository,
                          ClientFlxRepository clientFlxRepository,
                          TestScopes testScopes) {

        this.userFlxRepository = userFlxRepository;
        this.coachFlxRepository = coachFlxRepository;
        this.clientFlxRepository = clientFlxRepository;
    }

    // Setter injection
    //    @Autowired
    //    void setUserFlxRepository(UserFlxRepository userFlxRepository) {
    //        this.userFlxRepository = userFlxRepository;
    //    }


    public UserFlx createUser(UserFlx userFlx) {

        userFlx.setPassword(passwordEncoder.encode(userFlx.getPassword()));
        authGroupRepository.save(new AuthGroup(userFlx.getUsername(), "USER"));
        //        authGroupRepository.save(new AuthGroup(userFlx.getUsername(), "ADMIN"));
        return userFlxRepository.save(userFlx);
    }

    public UserFlx createAdmin(UserFlx userFlx) {

        userFlx.setPassword(passwordEncoder.encode(userFlx.getPassword()));
        authGroupRepository.save(new AuthGroup(userFlx.getUsername(), "ADMIN"));
        return userFlxRepository.save(userFlx);
    }

    public UserFlx createCoach(CoachFlx coachFlx) {
        String username=coachFlx.getUserFlx().getUsername();
        if (userFlxRepository.findByUsername(coachFlx.getUserFlx().getUsername()) != null) {
            throw new UserNameAlreadyTakenException("username "+ username+" is already taken!");

        } else {
            UserFlx newUser = coachFlx.getUserFlx();
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            UserFlx savedUser = userFlxRepository.save(newUser);
            authGroupRepository.save(new AuthGroup(newUser.getUsername(), "COACH"));

            coachFlx.setUserFlx(savedUser);

            coachFlxRepository.save(coachFlx);
            return savedUser;
        }
    }


    public ResponseEntity<UserFlx> createClient(ClientFlx clientFlx)  {
        String username=clientFlx.getUserFlx().getUsername();
        if (userFlxRepository.findByUsername(username) !=null){
            throw new UserNameAlreadyTakenException("username "+ username+" is already taken!");

        } else{
            UserFlx newUser = clientFlx.getUserFlx();
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            UserFlx savedUser = userFlxRepository.save(newUser);
            authGroupRepository.save(new AuthGroup(newUser.getUsername(), "CLIENT"));

            clientFlx.setUserFlx(savedUser);

            clientFlxRepository.save(clientFlx);
            String message= clientFlx.toString();
            messagePublisher.sendMessage(message);
            return new ResponseEntity<>(savedUser,HttpStatus.OK);
        }

    }


    public void deleteUser(Long id) {
        Optional<UserFlx> foundUser = userFlxRepository.findById(id);
        userFlxRepository.deleteById(foundUser.get().getId());
    }
    //This method is problematic
    public void deleteClientFlx(String username) {
        UserFlx searchedUser = userFlxRepository.findByUsername(username);

        LOGGER.info("deleteClientFlx called." );
        LOGGER.info("searchedUser: "+ searchedUser );

//        userFlxRepository.deleteById(searchedUser.getId());
        clientFlxRepository.deleteById(searchedUser.getId());
    }

    public void deleteClientFlx(Long id) {
        Optional<ClientFlx> foundClient = clientFlxRepository.findById(id);
        clientFlxRepository.deleteById(id);

    }

    public Optional<UserFlx> findUserById(Long id) {
        return userFlxRepository.findById(id);
    }

    public Iterable<UserFlx> findAllUsers() {
        return userFlxRepository.findAll();
    }

    public UserFlx getUserByUsername(String username) {
        return userFlxRepository.findByUsername(username);
    }


    public UserFlx getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUsername = authentication.getName();
        return getUserByUsername(authenticatedUsername);
    }


}
