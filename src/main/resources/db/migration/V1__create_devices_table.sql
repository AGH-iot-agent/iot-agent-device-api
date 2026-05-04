CREATE TABLE IF NOT EXISTS devices (
    id VARCHAR(128) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    status VARCHAR(32) NOT NULL,
    krakow_zone VARCHAR(64) NOT NULL,
    sample_interval_ms INTEGER NOT NULL,
    temperature_sensor_enabled BOOLEAN NOT NULL,
    humidity_sensor_enabled BOOLEAN NOT NULL,
    air_quality_sensor_enabled BOOLEAN NOT NULL,
    map_sensor_enabled BOOLEAN NOT NULL
);

ALTER TABLE devices ADD COLUMN IF NOT EXISTS krakow_zone VARCHAR(64) NOT NULL DEFAULT 'KROWODRZA';
ALTER TABLE devices ADD COLUMN IF NOT EXISTS sample_interval_ms INTEGER NOT NULL DEFAULT 5000;
ALTER TABLE devices ADD COLUMN IF NOT EXISTS temperature_sensor_enabled BOOLEAN NOT NULL DEFAULT true;
ALTER TABLE devices ADD COLUMN IF NOT EXISTS humidity_sensor_enabled BOOLEAN NOT NULL DEFAULT true;
ALTER TABLE devices ADD COLUMN IF NOT EXISTS air_quality_sensor_enabled BOOLEAN NOT NULL DEFAULT true;
ALTER TABLE devices ADD COLUMN IF NOT EXISTS map_sensor_enabled BOOLEAN NOT NULL DEFAULT true;
