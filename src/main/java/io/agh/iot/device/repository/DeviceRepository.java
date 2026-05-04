package io.agh.iot.device.repository;

import io.agh.iot.device.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, String> {
	List<Device> findAllByStatusIgnoreCase(String status);
}