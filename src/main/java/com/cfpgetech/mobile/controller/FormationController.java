package com.cfpgetech.mobile.controller;

import com.cfpgetech.mobile.entities.Departement;
import com.cfpgetech.mobile.entities.Formation;
import com.cfpgetech.mobile.repos.FormationRepository;
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
public class FormationController {

    @Autowired
    private FormationRepository formationRepository;

    @GetMapping("/formations")
    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
    }

    @GetMapping("/formations/{id}")
    public ResponseEntity<Formation> getFormationById(@PathVariable(value = "id") int formationId)
            throws ResourceNotFoundException {
        Formation formation = formationRepository.findById(formationId)
                .orElseThrow(() -> new ResourceNotFoundException("Formation not found for this id :: " + formationId));
        return ResponseEntity.ok().body(formation);
    }

    @PostMapping("/formations")
    public Formation createFormation(@Valid @RequestBody Formation formation) {
        return formationRepository.save(formation);
    }

    @PutMapping("/formations/{id}")
    public ResponseEntity<Formation> updateFormation(@PathVariable(value = "id") int formationId,
                                                         @Valid @RequestBody Formation formationDetails) throws ResourceNotFoundException {
        Formation formation = formationRepository.findById(formationId)
                .orElseThrow(() -> new ResourceNotFoundException("formation not found for this id :: " + formationId));

        formation.setNom(formationDetails.getNom());
        formation.setNumero(formationDetails.getNumero());
        formation.setDomaine(formationDetails.getDomaine());
        formation.setDuree(formationDetails.getDuree());

        final Formation updatedFormation = formationRepository.save(formation);
        return ResponseEntity.ok(updatedFormation);
    }

    @DeleteMapping("/formations/{id}")
    public Map<String, Boolean> deleteFormation(@PathVariable(value = "id") int formationId)
            throws ResourceNotFoundException {
        Formation formation = formationRepository.findById(formationId)
                .orElseThrow(() -> new ResourceNotFoundException("formation not found for this id :: " + formationId));

        formationRepository.delete(formation);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
