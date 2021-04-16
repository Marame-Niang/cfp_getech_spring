package com.cfpgetech.mobile.repos;

import com.cfpgetech.mobile.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role findRoleByLibelle(String libelle);
}
