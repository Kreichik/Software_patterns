package com.smarthome.devices.concrete;

import com.smarthome.devices.base.AbstractDevice;

public class Thermostat extends AbstractDevice {
    public static final String TEMPERATURE_PARAM = "temperature";

    public Thermostat(String name, String room) {
        super(name, 200.0, room);
        this.parameters.put(TEMPERATURE_PARAM, "21");
    }

    @Override
    public void operate() {
        if (isOn) {
            System.out.println("Thermostat '" + name + "' is maintaining " + getParameter(TEMPERATURE_PARAM) + "C");
        } else {
            System.out.println("Thermostat '" + name + "' is off");
        }
    }
}