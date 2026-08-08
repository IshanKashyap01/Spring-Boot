package com.example.patient_management_1.controller;

import com.example.patient_management_1.entity.Address;
import com.example.patient_management_1.service.AddressService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController
{
    private final AddressService service;

    public AddressController(AddressService service)
    {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable Long id)
    {
        return service.getAddress(id);
    }

    @PostMapping("/add/{patientId}")
    public Address createAddress(@PathVariable Long patientId , @RequestBody Address address)
    {
       return service.createAddress(patientId, address);
    }

    @PutMapping("/update")
    public Address updateAddress(@RequestBody Address address)
    {
        return service.updateAddress(address);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAddress(@PathVariable Long id)
    {
        service.deleteAddress(id);
    }
}