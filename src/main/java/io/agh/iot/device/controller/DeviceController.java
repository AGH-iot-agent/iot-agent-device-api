package io.agh.iot.device.controller;

import io.agh.iot.device.model.Device;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final Map<String, Device> devices = new ConcurrentHashMap<>();

    @GetMapping
    public List<Device> getAllDevices() {
        return new ArrayList<>(devices.values());
    }

    @PostMapping
    public ResponseEntity<Device> createDevice(@RequestBody Device device) {
        if (device.getId() == null || device.getId().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        devices.put(device.getId(), device);
        return ResponseEntity.status(HttpStatus.CREATED).body(device);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDevice(@PathVariable String id) {
        Device device = devices.get(id);
        return device != null ? ResponseEntity.ok(device) : ResponseEntity.notFound().build();
    }
}
