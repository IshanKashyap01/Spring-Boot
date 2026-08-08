package com.example.patient_management_1.service;

import com.example.patient_management_1.entity.*;
import com.example.patient_management_1.repository.*;
import jakarta.transaction.Transactional;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class DoctorService
{   
    private final DoctorRepository doctorRepository;
    final PatientRepository patientRepository;

    DoctorService(DoctorRepository doctorRepository, PatientRepository patientRepository)
    {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public Doctor getDoctor(Long id)
    {
        return doctorRepository.findById(id).get();
    }

    @Transactional
    public Doctor createDoctor(Long patientId, Doctor doctor)
    {
        Patient patient = patientRepository.findById(patientId).get();
        patient.setDoctor(doctor);
        doctor.getPatients().add(patient);
        return patient.getDoctor();
    }

    public Doctor updateDoctor(Doctor doctor)
    {
        return doctorRepository.save(doctor);   
    }

    // You need to use the similar approach as used for deleting address.
    @Transactional
    public void deleteDoctor(Long id)
    {
        List<Patient> patients = new ArrayList<>();
        patientRepository.findAll().forEach(patients::add);
        for(Patient patient : patients)
        {
            if(patient.getDoctor().getId().equals(id))
            {
                patient.setDoctor(null);
            }
        }
        doctorRepository.deleteById(id);
    }
}