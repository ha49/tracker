package com.example.demo.auth;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserFlxDetailsService implements UserDetailsService {

    private final UserFlxRepository userFlxRepository;

    public UserFlxDetailsService(UserFlxRepository userFlxRepository) {
        this.userFlxRepository = userFlxRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserFlx userFlx = this.userFlxRepository.findByUsername(username);
        if (userFlx == null) {
            throw new UsernameNotFoundException("Can't find username: " + username);
        }
        return new UserFlxPrincipal(userFlx);
    }
}
