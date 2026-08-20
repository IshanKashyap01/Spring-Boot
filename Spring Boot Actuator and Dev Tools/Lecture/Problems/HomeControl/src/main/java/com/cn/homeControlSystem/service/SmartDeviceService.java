package com.cn.homeControlSystem.service;

import com.cn.homeControlSystem.excpetion.InvalidStatusException;
import com.cn.homeControlSystem.dto.DeviceDTO;
import com.cn.homeControlSystem.model.SmartDevice;
import com.cn.homeControlSystem.repositories.SmartDevicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SmartDeviceService {

    @Autowired
    SmartDevicesRepository smartDevicesRepository;
    public List<SmartDevice> getAllDevices() {
    return smartDevicesRepository.findAll();
    }

    public SmartDevice getDeviceById(Integer id) {
        return smartDevicesRepository.findById(id).get();
    }

    public void addDevice(DeviceDTO dto) throws InvalidStatusException {
        smartDevicesRepository.save(new SmartDevice(null, dto.getName(), dto.getType(), dto.getStatus(), dto.getRoomId()));
    }

    public void updateDeviceStatus(DeviceDTO deviceDTO) {
    SmartDevice smartDevice=smartDevicesRepository.findByName(deviceDTO.getName());
    smartDevice.setStatus(deviceDTO.getStatus());
    smartDevice.setRoomId(smartDevice.getRoomId());

    smartDevicesRepository.save(smartDevice);
    }

    public void deleteDevice(Integer id) {
    smartDevicesRepository.deleteById(id);
    }
}
