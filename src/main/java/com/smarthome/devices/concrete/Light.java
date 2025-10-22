package com.smarthome.devices.concrete;

import com.smarthome.devices.base.AbstractDevice;

public class Light extends AbstractDevice {
    public static final String BRIGHTNESS_PARAM = "brightness";

    public Light(String name, String room) {
        super(name, 50.0, room);
        this.parameters.put(BRIGHTNESS_PARAM, "100");
    }

    @Override
    public void operate() {
        if (isOn) {
            System.out.println("Light '" + name + "' is " + getParameter(BRIGHTNESS_PARAM) + "% of brightness");
        } else {
            System.out.println("Light '" + name + "' is off");
        }
    }
}