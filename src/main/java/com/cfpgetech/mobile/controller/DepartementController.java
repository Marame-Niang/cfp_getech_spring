package com.cfpgetech.mobile.controller;

import com.cfpgetech.mobile.entities.Candidat;
import com.cfpgetech.mobile.entities.Departement;
import com.cfpgetech.mobile.repos.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class DepartementController {

    @Autowired
    private DepartementRepository departementRepository;

    @GetMapping("/departements")
    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    @GetMapping("/departements/{id}")
    public ResponseEntity<Departement> getDepartementById(@PathVariable(value = "id") int departementId)
            throws ResourceNotFoundException {
        Departement departement = departementRepository.findById(departementId)
                .orElseThrow(() -> new ResourceNotFoundException("Departement not found for this id :: " + departementId));
        return ResponseEntity.ok().body(departement);
    }

    @PostMapping("/departements")
    public Departement createDepartement(@Valid @RequestBody Departement departement) {
        return departementRepository.save(departement);
    }

    @PutMapping("/departements/{id}")
    public ResponseEntity<Departement> updateDepartement(@PathVariable(value = "id") int departementId,
                                                   @Valid @RequestBody Departement departementDetails) throws ResourceNotFoundException {
        Departement departement = departementRepository.findById(departementId)
                .orElseThrow(() -> new ResourceNotFoundException("departement not found for this id :: " + departementId));

        departement.setNom(departementDetails.getNom());

        final Departement updatedDepartement = departementRepository.save(departement);
        return ResponseEntity.ok(updatedDepartement);
    }

    @DeleteMapping("/departements/{id}")
    public Map<String, Boolean> deleteDepartement(@PathVariable(value = "id") int departementId)
            throws ResourceNotFoundException {
        Departement departement = departementRepository.findById(departementId)
                .orElseThrow(() -> new ResourceNotFoundException("departement not found for this id :: " + departementId));

        departementRepository.delete(departement);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
