package com.cfpgetech.mobile.security.services;

import com.cfpgetech.mobile.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User u = accountService.findUserByEmail(email);
        if(u==null){
            throw new UsernameNotFoundException(email);
        }
        return new UserPrincipale(u);
        //return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), authorities);
    }
}

