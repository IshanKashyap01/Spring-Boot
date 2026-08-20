package com.cn.homeControlSystem.repositories;

import com.cn.homeControlSystem.model.SmartDevice;
import org.springframework.data.jpa.repository.JpaRepository;
//extend JpaRepository with relevant generic types.
//add annotation for this Repository interface.
public interface SmartDevicesRepository extends JpaRepository<SmartDevice, Integer>
{
    //create a query method to fetch a device record by name from the database.
    SmartDevice findByName(String name);
}
