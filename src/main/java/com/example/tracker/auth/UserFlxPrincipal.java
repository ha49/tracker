package com.example.tracker.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserFlxPrincipal implements UserDetails {


    private UserFlx userFlx;
    private List<AuthGroup> authGroups;

    public UserFlxPrincipal(UserFlx userFlx, List<AuthGroup> authGroups) {
        super();
        this.userFlx = userFlx;
        this.authGroups = authGroups;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authGroups == null) {
            return Collections.emptySet();
        }
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        authGroups.forEach(group -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(group.getAuthGroup()));
        });

        return grantedAuthorities;
    }


    @Override
    public String getPassword() {
        return this.userFlx.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userFlx.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
