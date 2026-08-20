package com.cn.homeControlSystem.controller;

import com.cn.homeControlSystem.dto.DeviceDTO;
import com.cn.homeControlSystem.excpetion.InvalidStatusException;
import com.cn.homeControlSystem.model.SmartDevice;
import com.cn.homeControlSystem.service.SmartDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/devices")
public class DevicesController
{
    //Autowire the SmartDeviceService object;
    private final SmartDeviceService service;
    //complete the following method bodies as stated.

    /**
     1. Call the required service method
     2. Add proper annotation for GET Mapping .
     **/
    @GetMapping("/all")
    public List<SmartDevice> getAllDevices()
    {
        return service.getAllDevices();
    }

    /**
     1. Call the required service method
     2. Add proper annotation for GET Mapping and attach the required pathvariable to the method parameter.
     **/
    @GetMapping("id/{id}")
    public SmartDevice getDeviceById(@PathVariable int id)
    {
        return service.getDeviceById(id);
    }

    /**
     1. Call the required service method
     2. Add proper annotation for POST Mapping and attach the required request body to the method parameter.
     3. Surround InvalidStatusException with try - catch.
     * @throws InvalidStatusException 
     **/
    @PostMapping("/add")
    public void addDevice(@RequestBody DeviceDTO dto) throws InvalidStatusException
    {
        if (dto.getStatus().equalsIgnoreCase("On") ||dto.getStatus().equalsIgnoreCase("Off"))
         service.addDevice(dto);
        else
            throw new InvalidStatusException("Invalid Status");
    }

    /**
     1. Call the required service method
     2. Add proper annotation for PUT Mapping and attach the required request body to the method parameter.
     3.  Surround InvalidStatusException with try - catch.
     **/
    @PutMapping("/changeStatus")
    public void updateDeviceStatus(@RequestBody DeviceDTO dto) throws InvalidStatusException
    {
        if (dto.getStatus().equalsIgnoreCase("On") ||dto.getStatus().equalsIgnoreCase("Off"))
            service.updateDeviceStatus(dto);
        else
            throw new InvalidStatusException("Invalid Status");
    }

   // Create the new delete api later after running the application.
    @DeleteMapping("/delete/id/{id}")
    public void deleteDevice(@PathVariable int id)
    {
        service.deleteDevice(id);
    }
}