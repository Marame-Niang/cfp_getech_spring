package com.cfpgetech.mobile.controller;

import com.cfpgetech.mobile.entities.Programme;
import com.cfpgetech.mobile.entities.Responsable;
import com.cfpgetech.mobile.repos.ResponsableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ResponsableController {

    @Autowired
    private ResponsableRepository responsableRepository;

    @GetMapping("/responsables")
    public List<Responsable> getAllResponsables() {
        return responsableRepository.findAll();
    }

    @GetMapping("/responsables/{id}")
    public ResponseEntity<Responsable> getResponsableById(@PathVariable(value = "id") int responsableId)
            throws ResourceNotFoundException {
        Responsable responsable = responsableRepository.findById(responsableId)
                .orElseThrow(() -> new ResourceNotFoundException("Responsable not found for this id :: " + responsableId));
        return ResponseEntity.ok().body(responsable);
    }

    @PostMapping("/responsables")
    public Responsable createResponsable(@Valid @RequestBody Responsable responsable) {
        return responsableRepository.save(responsable);
    }

    @PutMapping("/responsables/{id}")
    public ResponseEntity<Responsable> updateResponsable(@PathVariable(value = "id") int responsableId,
                                                     @Valid @RequestBody Responsable responsableDetails) throws ResourceNotFoundException {
        Responsable responsable = responsableRepository.findById(responsableId)
                .orElseThrow(() -> new ResourceNotFoundException("Responsable not found for this id :: " + responsableId));

        responsable.setNom(responsableDetails.getNom());
        responsable.setPrenom(responsableDetails.getPrenom());
        responsable.setMatricule(responsableDetails.getMatricule());
        responsable.setAdresse(responsableDetails.getAdresse());
        responsable.setTelephone(responsableDetails.getTelephone());

        final Responsable updatedResponsable = responsableRepository.save(responsable);
        return ResponseEntity.ok(updatedResponsable);
    }

    @DeleteMapping("/responsables/{id}")
    public Map<String, Boolean> deleteResponsable(@PathVariable(value = "id") int responsableId)
            throws ResourceNotFoundException {
        Responsable responsable = responsableRepository.findById(responsableId)
                .orElseThrow(() -> new ResourceNotFoundException("Responsable not found for this id :: " + responsableId));

        responsableRepository.delete(responsable);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
