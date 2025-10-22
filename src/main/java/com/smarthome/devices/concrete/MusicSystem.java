package com.smarthome.devices.concrete;

import com.smarthome.devices.base.AbstractDevice;

public class MusicSystem extends AbstractDevice {
    public static final String VOLUME_PARAM = "volume";
    public static final String TRACK_PARAM = "track";

    public MusicSystem(String name, String room) {
        super(name, 120.0, room);
        this.parameters.put(VOLUME_PARAM, "50");
        this.parameters.put(TRACK_PARAM, "Default Playlist");
    }

    @Override
    public void operate() {
        if (isOn) {
            System.out.println("Music System '" + name + "' is playing '" + getParameter(TRACK_PARAM) + "' at " + getParameter(VOLUME_PARAM) + "% volume");
        } else {
            System.out.println("Music System '" + name + "' is off");
        }
    }
}