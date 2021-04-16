package com.cfpgetech.mobile.security.services;

import com.cfpgetech.mobile.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

public class UserPrincipale implements UserDetails  {

    private User user;
    private Collection<SimpleGrantedAuthority> authorities;

    public UserPrincipale(User user) {
          this.user = user;
        this.authorities = new ArrayList<>();
    }

    @Transactional
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        this.user.getRoles().forEach(r->{

             if (r.getLibelle().equalsIgnoreCase("ADMIN")){
                this.authorities.add(new SimpleGrantedAuthority(r.getLibelle()));
            }
            else if (r.getLibelle().equalsIgnoreCase("CANDIDAT")){
                this.authorities.add(new SimpleGrantedAuthority(r.getLibelle()));
            }

        });
        return this.authorities;

    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
         return this.user.getEmail();
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
