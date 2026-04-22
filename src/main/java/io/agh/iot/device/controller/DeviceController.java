package io.agh.iot.device.controller;

import io.agh.iot.device.model.Device;
import io.agh.iot.device.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceService deviceService;

    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public List<Device> getAllDevices(@RequestParam(required = false) String status) {
        logger.info("Fetching all devices{}", status != null ? " with status: " + status : "");
        return deviceService.findAllByStatus(status);
    }

    @PostMapping
    public ResponseEntity<Device> createDevice(@RequestBody Device device) {
        logger.info("Creating new device: {}", device);
        if (device.getId() == null || device.getId().isBlank()) {
            logger.warn("Invalid device creation request: missing ID");
            return ResponseEntity.badRequest().build();
        }

        Device savedDevice = deviceService.save(device);
        logger.info("Device created: {}", savedDevice);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDevice);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDevice(@PathVariable String id) {
        logger.info("Fetching device with ID: {}", id);
        return deviceService.findById(id)
            .map(device -> {
                logger.info("Device found: {}", device);
                return ResponseEntity.ok(device);
            })
            .orElseGet(() -> {
                logger.warn("Device not found with ID: {}", id);
                return ResponseEntity.notFound().build();
            });
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable String id, @RequestBody Device device) {
        logger.info("Updating device with ID: {} with data: {}", id, device);
        return deviceService.update(id, device)
            .map(updated -> {
                Device saved = deviceService.save(updated);
                logger.info("Device updated: {}", saved);
                return ResponseEntity.ok(saved);
            })
            .orElseGet(() -> {
                logger.warn("Device to update not found with ID: {}", id);
                return ResponseEntity.notFound().build();
            });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable String id) {
        logger.info("Deleting device with ID: {}", id);
        if (!deviceService.deleteById(id)) {
            logger.warn("Device to delete not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
        logger.info("Device deleted with ID: {}", id);
        return ResponseEntity.noContent().build();
    }
}
