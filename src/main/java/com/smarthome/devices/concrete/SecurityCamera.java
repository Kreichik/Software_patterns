package com.smarthome.devices.concrete;

import com.smarthome.devices.base.AbstractDevice;

public class SecurityCamera extends AbstractDevice {
    public static final String RECORDING_PARAM = "recording";

    public SecurityCamera(String name, String room) {
        super(name, 80.0, room);
        this.parameters.put(RECORDING_PARAM, "false");
    }

    @Override
    public void operate() {
        if (isOn) {
            boolean isRecording = Boolean.parseBoolean(getParameter(RECORDING_PARAM));
            System.out.println("Security Camera '" + name + "' is active. Recording: " + isRecording);
        } else {
            System.out.println("Security Camera '" + name + "' is off");
        }
    }
}