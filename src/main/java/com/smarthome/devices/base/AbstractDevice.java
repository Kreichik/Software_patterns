package com.smarthome.devices.base;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDevice implements Device {
    protected String name;
    protected boolean isOn;
    protected double basePowerConsumption;
    protected Map<String, String> parameters;
    protected String location;


    public AbstractDevice(String name, double basePowerConsumption, String room) {
        this.name = name;
        this.isOn = false;
        this.basePowerConsumption = basePowerConsumption;
        this.parameters = new HashMap<>();
        this.location = room;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getStatus() {
        return name + " is " + (isOn ? "On" : "Off");
    }

    @Override
    public void turnOn() {
        this.isOn = true;
        System.out.println(name + " has been turned On");
    }

    @Override
    public void turnOff() {
        this.isOn = false;
        System.out.println(name + " has been turned Off");
    }

    @Override
    public double getPowerConsumption() {
        return isOn ? basePowerConsumption : 0;
    }

    @Override
    public void setParameter(String key, String value) {
        if (parameters.containsKey(key)) {
            parameters.put(key, value);
            System.out.println("Parameter '" + key + "' for " + name + " set to '" + value + "'");
        } else {
            System.out.println(name + " does not have parameter '" + key + "'");
        }
    }

    @Override
    public String getParameter(String key) {
        return parameters.get(key);
    }

    @Override
    public Map<String, String> getAllParameters() {
        return new HashMap<>(parameters);
    }

    @Override
    public String getDeviceType() {
        return this.getClass().getSimpleName();
    }

    @Override
    public abstract void operate();

    @Override
    public void executeVoiceCommand(String command) {
        System.out.println("'" + getName() + "' not support voice commands");
    }

    @Override
    public String getBaseName() {
        return this.name;
    }

    @Override
    public String getLocation(){
        return this.location;
    }
}