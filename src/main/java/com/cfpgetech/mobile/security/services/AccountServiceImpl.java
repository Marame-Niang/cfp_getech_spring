package com.cfpgetech.mobile.security.services;

import com.cfpgetech.mobile.entities.Role;
import com.cfpgetech.mobile.entities.User;
import com.cfpgetech.mobile.repos.RoleRepository;
import com.cfpgetech.mobile.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User saveUser(@Valid @RequestBody User user) {
        String hashPw = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPw);
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(@Valid @RequestBody Role role)
    {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByEmail(String email)
            throws ResourceNotFoundException{

        User u = this.userRepository.findByEmail(email);
        //.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + username));;
        return u;
    }

    @Override
    public ResponseEntity<Role> findRoleByLibelle(@PathVariable(value = "libelle") String libelle) throws ResourceNotFoundException {
        Role role = roleRepository.findRoleByLibelle(libelle);
        return ResponseEntity.ok().body(role);
    }

    @Override
    public void addRoleToUser(String username, String libelle) {
        Role role = roleRepository.findRoleByLibelle(libelle);
        User user = userRepository.findByUsername(username);
        user.getRoles().add(role);
    }


}
