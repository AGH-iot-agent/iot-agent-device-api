-- V2: Recreate devices table to match new entity schema (id VARCHAR primary key)
-- Old schema used BIGSERIAL id + device_id VARCHAR(64), new schema uses user-provided VARCHAR id

DROP TABLE IF EXISTS devices;

CREATE TABLE devices (
    id VARCHAR(128) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    status VARCHAR(32) NOT NULL DEFAULT 'ACTIVE',
    krakow_zone VARCHAR(64) NOT NULL DEFAULT 'KROWODRZA',
    sample_interval_ms INTEGER NOT NULL DEFAULT 5000,
    temperature_sensor_enabled BOOLEAN NOT NULL DEFAULT true,
    humidity_sensor_enabled BOOLEAN NOT NULL DEFAULT true,
    air_quality_sensor_enabled BOOLEAN NOT NULL DEFAULT true,
    map_sensor_enabled BOOLEAN NOT NULL DEFAULT true
);
