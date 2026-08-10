package com.example.patient_management_1.controller;

import com.example.patient_management_1.entity.Patient;
import com.example.patient_management_1.service.PatientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController
{
    private final PatientService patientService;

    PatientController(PatientService patientService) 
    {
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public Patient getPatients(@PathVariable Long id)
    {
        return patientService.getPatient(id);        
    }

    @PostMapping("/add")
    public Patient createPatient(@RequestBody Patient patient)
    {
        return patientService.createPatient(patient);       
    }
    
    @PutMapping("/update")
    public Patient updatePatient(@RequestBody Patient patient)
    {
        return patientService.updatePatient(patient);      
    }
    
    @DeleteMapping("/delete/{id}")
    public void deletePatient(@PathVariable Long id)
    {
        patientService.deletePatient(id);
    }
}