package com.example.patient_management_1.service;

import com.example.patient_management_1.entity.Address;
import com.example.patient_management_1.entity.Patient;
import com.example.patient_management_1.repository.AddressRepository;
import com.example.patient_management_1.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AddressService
{
    private final AddressRepository addressRepository;
    private final PatientRepository patientRepository;

    public AddressService(AddressRepository addressRepository, PatientRepository patientRepository)
    {
        this.addressRepository = addressRepository;
        this.patientRepository = patientRepository;
    }

    public Address getAddress(Long id)
    {
        return addressRepository.findById(id).get();
    }

    @Transactional
    public Address createAddress(Long patientId,Address address)
    {
        Patient patient = patientRepository.findById(patientId).get();
        patient.setAddress(address);
        return patient.getAddress();
    }

    public Address updateAddress(Address address)
    {
        return addressRepository.save(address);        
    }
    /* 
    Explanation:
    1. We cannot delete address directly using jparepository deleteById method due to 
        relational mapping with the patient class.
    2. So before deleting address you need find the patient with same address and remove 
        the linked address details.
    3. And finally save the patient and delete the address using jpa deleteById method.   
    */
    public void deleteAddress(Long id)
    {
        for(Patient patient : patientRepository.findAll())
        {
            if(patient.getAddress() != null && patient.getAddress().getId().equals(id))
            {
                patient.setAddress(null);
                patientRepository.save(patient);
                addressRepository.deleteById(id);
            }
        }
    }
}