package io.agh.iot.device.service;

import io.agh.iot.device.model.Device;
import io.agh.iot.device.repository.DeviceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    public List<Device> findAllByStatus(String status) {
        if (status == null || status.isBlank()) {
            return findAll();
        }
        return deviceRepository.findAllByStatusIgnoreCase(status);
    }

    public Device save(Device device) {
        if (device.getStatus() == null || device.getStatus().isBlank()) {
            device.setStatus("ONLINE");
        }
        if (device.getSampleIntervalMs() == null || device.getSampleIntervalMs() < 500) {
            device.setSampleIntervalMs(5000);
        }
        if (device.getTemperatureSensorEnabled() == null) {
            device.setTemperatureSensorEnabled(Boolean.TRUE);
        }
        if (device.getHumiditySensorEnabled() == null) {
            device.setHumiditySensorEnabled(Boolean.TRUE);
        }
        if (device.getAirQualitySensorEnabled() == null) {
            device.setAirQualitySensorEnabled(Boolean.TRUE);
        }
        if (device.getMapSensorEnabled() == null) {
            device.setMapSensorEnabled(Boolean.TRUE);
        }
        if (device.getKrakowZone() == null || device.getKrakowZone().isBlank()) {
            device.setKrakowZone("KROWODRZA");
        }
        return deviceRepository.save(device);
    }

    public Optional<Device> findById(String id) {
        return deviceRepository.findById(id);
    }

    @Transactional
    public Optional<Device> update(String id, Device incoming) {
        return deviceRepository.findById(id)
            .map(existing -> {
                existing.setName(incoming.getName() != null ? incoming.getName() : existing.getName());
                existing.setStatus(incoming.getStatus() != null ? incoming.getStatus() : existing.getStatus());
                existing.setKrakowZone(incoming.getKrakowZone() != null ? incoming.getKrakowZone() : existing.getKrakowZone());
                existing.setSampleIntervalMs(incoming.getSampleIntervalMs() != null ? incoming.getSampleIntervalMs() : existing.getSampleIntervalMs());
                existing.setTemperatureSensorEnabled(incoming.getTemperatureSensorEnabled() != null ? incoming.getTemperatureSensorEnabled() : existing.getTemperatureSensorEnabled());
                existing.setHumiditySensorEnabled(incoming.getHumiditySensorEnabled() != null ? incoming.getHumiditySensorEnabled() : existing.getHumiditySensorEnabled());
                existing.setAirQualitySensorEnabled(incoming.getAirQualitySensorEnabled() != null ? incoming.getAirQualitySensorEnabled() : existing.getAirQualitySensorEnabled());
                existing.setMapSensorEnabled(incoming.getMapSensorEnabled() != null ? incoming.getMapSensorEnabled() : existing.getMapSensorEnabled());
                return existing;
            });
    }

    public boolean deleteById(String id) {
        if (!deviceRepository.existsById(id)) {
            return false;
        }
        deviceRepository.deleteById(id);
        return true;
    }
}