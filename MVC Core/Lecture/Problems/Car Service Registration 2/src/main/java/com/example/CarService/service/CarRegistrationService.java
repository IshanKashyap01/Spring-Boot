package com.example.CarService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CarService.domain.Vehicle;

/**

 1. Implement the interface Registration and Override the method registerCar() and getNewCar() in CarRegistrationService.
 2. Also,autowire car of type Vehicle and use it in method registerCar() and getNewCar().

**/

@Service
public class CarRegistrationService implements Registration
{
    @Autowired
    Vehicle car;

    @Override
    public Boolean registerCar(String vehicleNo, String vehicleName, String carDetails, String carWork)
    {
        if(vehicleNo == null || vehicleName == null || carDetails == null || carWork == null)
        {
            return false;
        }
        this.car.createVehicle(vehicleNo, vehicleName, carDetails, carWork);
        this.car.saveVehicleDetails();
        return true;
    }

    @Override
    public Vehicle getNewCar()
    {
        return this.car;
    }
}