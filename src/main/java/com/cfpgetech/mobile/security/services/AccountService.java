package com.cfpgetech.mobile.security.services;

import com.cfpgetech.mobile.entities.Role;
import com.cfpgetech.mobile.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AccountService {
    public User saveUser(@Valid @RequestBody User user);
//  public User findUserByUsername(String username);
    public User findUserByEmail(String email);

    public Role saveRole(@Valid @RequestBody Role role);
    public ResponseEntity<Role> findRoleByLibelle(String libelle);

    public void addRoleToUser(String username, String libelle);

}
