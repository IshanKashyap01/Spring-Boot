package com.example.patient_management_1.controller;

import com.example.patient_management_1.entity.Doctor;
import com.example.patient_management_1.service.DoctorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController
{
    private final DoctorService service;

    DoctorController(DoctorService doctorService)
    {
        this.service = doctorService;
    }
    
    @GetMapping("/{id}")
    public Doctor getDoctor(@PathVariable Long id)
    {
        return service.getDoctor(id);
    }
    
    @PostMapping("/add/{patientId}")
    public Doctor createDoctor(@PathVariable Long patientId,@RequestBody Doctor doctor)
    {
        return service.createDoctor(patientId, doctor);
    }

    @PutMapping("/update")
    public Doctor updateDoctor(@RequestBody Doctor doctor)
    {
        return service.updateDoctor(doctor);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDoctor(@PathVariable Long id)
    {
        service.deleteDoctor(id);
    }
}