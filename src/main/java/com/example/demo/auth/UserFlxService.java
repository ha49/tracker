package com.example.demo.auth;


import com.example.demo.entity.CoachFlx;
import com.example.demo.repository.CoachFlxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserFlxService {

    @Autowired
    AuthGroupRepository authGroupRepository;

    @Autowired
    CoachFlxRepository coachFlxRepository;

    private UserFlxRepository userFlxRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Constructor injection
    public UserFlxService(UserFlxRepository userFlxRepository,CoachFlxRepository coachFlxRepository,
                          TestScopes testScopes) {

        this.userFlxRepository = userFlxRepository;
        this.coachFlxRepository=coachFlxRepository;
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

    public UserFlx createCoach(CoachFlx coachFlx){

        UserFlx newUser= coachFlx.getUserFlx();
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        UserFlx savedUser=userFlxRepository.save(newUser);
        authGroupRepository.save(new AuthGroup(newUser.getUsername(), "COACH"));

        coachFlx.setUserFlx(savedUser);

        coachFlxRepository.save(coachFlx);
        return savedUser;
    }


    public void deleteUser(Long id) {
        Optional<UserFlx> foundUser = userFlxRepository.findById(id);
        userFlxRepository.deleteById(foundUser.get().getId());
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
