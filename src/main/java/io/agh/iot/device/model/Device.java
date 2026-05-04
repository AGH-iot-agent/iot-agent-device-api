package io.agh.iot.device.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "devices")
public class Device {

    @Id
    private String id;
    private String name;
    private String status;
    private String krakowZone;
    private Integer sampleIntervalMs;
    private Boolean temperatureSensorEnabled;
    private Boolean humiditySensorEnabled;
    private Boolean airQualitySensorEnabled;
    private Boolean mapSensorEnabled;

    public Device() {
        // Required by JPA
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKrakowZone() {
        return krakowZone;
    }

    public void setKrakowZone(String krakowZone) {
        this.krakowZone = krakowZone;
    }

    public Integer getSampleIntervalMs() {
        return sampleIntervalMs;
    }

    public void setSampleIntervalMs(Integer sampleIntervalMs) {
        this.sampleIntervalMs = sampleIntervalMs;
    }

    public Boolean getTemperatureSensorEnabled() {
        return temperatureSensorEnabled;
    }

    public void setTemperatureSensorEnabled(Boolean temperatureSensorEnabled) {
        this.temperatureSensorEnabled = temperatureSensorEnabled;
    }

    public Boolean getHumiditySensorEnabled() {
        return humiditySensorEnabled;
    }

    public void setHumiditySensorEnabled(Boolean humiditySensorEnabled) {
        this.humiditySensorEnabled = humiditySensorEnabled;
    }

    public Boolean getAirQualitySensorEnabled() {
        return airQualitySensorEnabled;
    }

    public void setAirQualitySensorEnabled(Boolean airQualitySensorEnabled) {
        this.airQualitySensorEnabled = airQualitySensorEnabled;
    }

    public Boolean getMapSensorEnabled() {
        return mapSensorEnabled;
    }

    public void setMapSensorEnabled(Boolean mapSensorEnabled) {
        this.mapSensorEnabled = mapSensorEnabled;
    }
}
