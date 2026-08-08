package com.example.patient_management_1.service;

import com.example.patient_management_1.entity.Patient;
import com.example.patient_management_1.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService
{
    private final PatientRepository patientRepository;

    PatientService(PatientRepository patientRepository)
    {
        this.patientRepository = patientRepository;
    }

    public Patient getPatient(Long id)
    {
        return patientRepository.findById(id).get();        
    }
    
    public Patient createPatient(Patient patient)
    {
        return patientRepository.save(patient);        
    }

    public Patient updatePatient(Patient patient)
    {
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id)
    {
        patientRepository.deleteById(id);       
    }
}