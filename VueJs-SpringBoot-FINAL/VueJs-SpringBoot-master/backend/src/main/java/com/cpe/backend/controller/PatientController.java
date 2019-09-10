package com.cpe.backend.controller;

import com.cpe.backend.entity.Patient;
import com.cpe.backend.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PatientController {

    @Autowired
    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patient")
    public Collection<Patient> Patients() {
        return patientRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/patient/{id}")
    public Optional<Patient> Patients(@PathVariable Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient;
    }

}