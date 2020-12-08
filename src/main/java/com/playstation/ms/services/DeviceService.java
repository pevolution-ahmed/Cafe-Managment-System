package com.playstation.ms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playstation.ms.models.Device;
@Service

public class DeviceService {
	@Autowired
	private DeviceRepository deviceRepository;
	
	public List<Device> getAll(){
		return deviceRepository.findAll();
	}
	
	public Device getOne(int id) {
		return deviceRepository.findById(id).get();
	}
	public Device getByName(String name) {
		return deviceRepository.findByName(name);
	}
	
	public Device saveDevice(Device device) {
		return deviceRepository.save(device);
	}
	
	public String deleteDevice(int id) {
		deviceRepository.deleteById(id);
		return "Device "+id+" have been deleted";
	}

}
