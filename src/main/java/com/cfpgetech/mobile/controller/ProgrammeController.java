package com.cfpgetech.mobile.controller;

import com.cfpgetech.mobile.entities.Programme;
import com.cfpgetech.mobile.repos.ProgrammeRepository;
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
public class ProgrammeController {

    @Autowired
    private ProgrammeRepository programmeRepository;

    @GetMapping("/programmes")
    public List<Programme> getAllProgrammes() {
        return programmeRepository.findAll();
    }

    @GetMapping("/programmes/{id}")
    public ResponseEntity<Programme> getProgrammeById(@PathVariable(value = "id") int programmeId)
            throws ResourceNotFoundException {
        Programme programme = programmeRepository.findById(programmeId)
                .orElseThrow(() -> new ResourceNotFoundException("Programme not found for this id :: " + programmeId));
        return ResponseEntity.ok().body(programme);
    }

    @PostMapping("/programmes")
    public Programme createProgramme(@Valid @RequestBody Programme programme) {
        return programmeRepository.save(programme);
    }

    @PutMapping("/programmes/{id}")
    public ResponseEntity<Programme> updateProgramme(@PathVariable(value = "id") int programmeId,
                                                     @Valid @RequestBody Programme programmeDetails) throws ResourceNotFoundException {
        Programme programme = programmeRepository.findById(programmeId)
                .orElseThrow(() -> new ResourceNotFoundException("Programme not found for this id :: " + programmeId));

        programme.setNom(programmeDetails.getNom());
        programme.setDescription(programmeDetails.getDescription());


        final Programme updatedProgramme = programmeRepository.save(programme);
        return ResponseEntity.ok(updatedProgramme);
    }

    @DeleteMapping("/programmes/{id}")
    public Map<String, Boolean> deleteProgramme(@PathVariable(value = "id") int programmeId)
            throws ResourceNotFoundException {
        Programme programme = programmeRepository.findById(programmeId)
                .orElseThrow(() -> new ResourceNotFoundException("programme not found for this id :: " + programmeId));

        programmeRepository.delete(programme);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
