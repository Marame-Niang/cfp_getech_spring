package com.cfpgetech.mobile.repos;

import com.cfpgetech.mobile.entities.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatRepository extends JpaRepository<Candidat, Integer> {
}
