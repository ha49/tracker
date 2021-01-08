package com.example.demo.auth;


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

    private UserFlxRepository userFlxRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Constructor injection
    public UserFlxService(UserFlxRepository userFlxRepository, TestScopes testScopes) {
        this.userFlxRepository = userFlxRepository;
    }

    // Setter injection
    //    @Autowired
    //    void setUserFlxRepository(UserFlxRepository userFlxRepository) {
    //        this.userFlxRepository = userFlxRepository;
    //    }


    public UserFlx createUser(UserFlx userFlx) {

        userFlx.setPassword(passwordEncoder.encode(userFlx.getPassword()));
        authGroupRepository.save(new AuthGroup(userFlx.getUsername(), "USER"));
        authGroupRepository.save(new AuthGroup(userFlx.getUsername(), "ADMIN"));
        return userFlxRepository.save(userFlx);
    }

    public UserFlx createCoach(UserFlx userFlx){
        userFlx.setPassword(passwordEncoder.encode(userFlx.getPassword()));
        authGroupRepository.save(new AuthGroup(userFlx.getUsername(), "COACH"));
        return userFlxRepository.save(userFlx);
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
