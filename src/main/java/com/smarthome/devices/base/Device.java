package com.smarthome.devices.base;

import java.util.Map;

public interface Device {
    void operate();
    String getName();
    String getStatus();
    void turnOn();
    void turnOff();
    double getPowerConsumption();
    void setParameter(String key, String value);
    String getParameter(String key);
    Map<String, String> getAllParameters();
    String getDeviceType();
    void executeVoiceCommand(String command);
    String getBaseName();
    String getLocation();
}