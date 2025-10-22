package com.smarthome.decorators;

import com.smarthome.devices.base.Device;
import java.util.Map;

public abstract class DeviceDecorator implements Device {
    protected Device decoratedDevice;

    public DeviceDecorator(Device decoratedDevice) {
        this.decoratedDevice = decoratedDevice;
    }

    @Override
    public void operate() {
        decoratedDevice.operate();
    }

    @Override
    public String getName() {
        return decoratedDevice.getName();
    }

    @Override
    public String getStatus() {
        return decoratedDevice.getStatus();
    }

    @Override
    public void turnOn() {
        decoratedDevice.turnOn();
    }

    @Override
    public void turnOff() {
        decoratedDevice.turnOff();
    }

    @Override
    public double getPowerConsumption() {
        return decoratedDevice.getPowerConsumption();
    }

    @Override
    public void setParameter(String key, String value) {
        decoratedDevice.setParameter(key, value);
    }

    @Override
    public String getParameter(String key) {
        return decoratedDevice.getParameter(key);
    }

    @Override
    public Map<String, String> getAllParameters() {
        return decoratedDevice.getAllParameters();
    }

    @Override
    public String getDeviceType() {
        return decoratedDevice.getDeviceType();
    }

    @Override
    public void executeVoiceCommand(String command) {
        decoratedDevice.executeVoiceCommand(command);
    }

    @Override
    public String getBaseName() {
        return decoratedDevice.getBaseName();
    }

    @Override
    public String getLocation() {
        return decoratedDevice.getLocation();
    }
}