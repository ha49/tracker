package com.example.demo.auth;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFlxDetailsService implements UserDetailsService {


    private final UserFlxRepository userFlxRepository;
    private final AuthGroupRepository authGroupRepository;

    public UserFlxDetailsService(UserFlxRepository userFlxRepository, AuthGroupRepository authGroupRepository) {
        this.userFlxRepository = userFlxRepository;
        this.authGroupRepository = authGroupRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserFlx userFlx = this.userFlxRepository.findByUsername(username);
        if (userFlx == null) {
            throw new UsernameNotFoundException("Can't find username: " + username);
        }
        List<AuthGroup> authGroups = this.authGroupRepository.findByUsername(username);
        return new UserFlxPrincipal(userFlx, authGroups);
    }


}
