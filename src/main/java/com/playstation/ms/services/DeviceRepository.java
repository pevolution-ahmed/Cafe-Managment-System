package com.playstation.ms.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.playstation.ms.models.Device;

public interface DeviceRepository extends JpaRepository<Device, Integer>{

	Device findByName(String name);
}
