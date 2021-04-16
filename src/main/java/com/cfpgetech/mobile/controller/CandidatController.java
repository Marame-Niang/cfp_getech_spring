package com.cfpgetech.mobile.controller;

import com.cfpgetech.mobile.entities.Candidat;
import com.cfpgetech.mobile.repos.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CandidatController {

    @Autowired
    private CandidatRepository candidatRepository;

    @GetMapping("/candidats")
    public List<Candidat> getAllCandidats() {
        return candidatRepository.findAll();
    }

    @GetMapping("/candidats/{id}")
    public ResponseEntity<Candidat> getCandidatById(@PathVariable(value = "id") int candidatId)
            throws ResourceNotFoundException {
        Candidat candidat = candidatRepository.findById(candidatId)
                .orElseThrow(() -> new ResourceNotFoundException("candidat not found for this id :: " + candidatId));
        return ResponseEntity.ok().body(candidat);
    }

    @PostMapping("/candidats")
    public Candidat createCandidat(@Valid @RequestBody Candidat candidat) {
        return candidatRepository.save(candidat);
    }

    @PutMapping("/candidats/{id}")
    public ResponseEntity<Candidat> updateCandidat(@PathVariable(value = "id") int candidatId,
                                             @Valid @RequestBody Candidat candidatDetails) throws ResourceNotFoundException {
        Candidat candidat = candidatRepository.findById(candidatId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidat not found for this id :: " + candidatId));

        candidat.setNom(candidatDetails.getNom());
        candidat.setPrenom(candidatDetails.getPrenom());
        candidat.setAdresse(candidatDetails.getAdresse());
        candidat.setTelephone(candidatDetails.getTelephone());

        final Candidat updatedCandidat = candidatRepository.save(candidat);
        return ResponseEntity.ok(updatedCandidat);
    }

    @DeleteMapping("/candidats/{id}")
    public Map<String, Boolean> deleteCandidat(@PathVariable(value = "id") int candidatId)
            throws ResourceNotFoundException {
        Candidat candidat = candidatRepository.findById(candidatId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidat not found for this id :: " + candidatId));

        candidatRepository.delete(candidat);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
