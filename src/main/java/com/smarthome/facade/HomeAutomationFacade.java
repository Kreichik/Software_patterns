package com.smarthome.facade;

import com.smarthome.devices.base.Device;
import com.smarthome.devices.concrete.Light;
import com.smarthome.devices.concrete.MusicSystem;
import com.smarthome.devices.concrete.SecurityCamera;
import com.smarthome.devices.concrete.Thermostat;

import java.util.List;

public class HomeAutomationFacade {
    private List<Device> devices;

    public HomeAutomationFacade(List<Device> devices) {
        this.devices = devices;
    }

    public void activateNightMode() {
        System.out.println("\n--- Activating Night Mode ---");
        for (Device device : devices) {
            if (device.getDeviceType().equals("Light")) {
                device.turnOff();
            } else if (device.getDeviceType().equals("Thermostat")) {
                device.turnOn();
                device.setParameter(Thermostat.TEMPERATURE_PARAM, "18");
            } else if (device.getDeviceType().equals("SecurityCamera")) {
                device.turnOn();
                device.setParameter(SecurityCamera.RECORDING_PARAM, "true");
            } else if (device.getDeviceType().equals("MusicSystem")){
                device.turnOff();
            }
        }
        System.out.println("--- Night Mode is active ---");
    }

    public void startPartyMode() {
        System.out.println("\n--- Starting Party Mode ---");
        for (Device device : devices) {
            if (device.getDeviceType().equals("Light")) {
                device.turnOn();
                device.setParameter(Light.BRIGHTNESS_PARAM, "70");
            } else if (device.getDeviceType().equals("MusicSystem")) {
                device.turnOn();
                device.setParameter(MusicSystem.VOLUME_PARAM, "90");
                device.setParameter(MusicSystem.TRACK_PARAM, "Party Playlist");
            } else if (device.getDeviceType().equals("SecurityCamera")) {
                device.turnOff();
            }
        }
        System.out.println("--- Party Mode is active ---");
    }

    public void leaveHome() {
        System.out.println("\n--- Activating Leave Home Mode ---");
        for (Device device : devices) {
            if (device.getDeviceType().equals("SecurityCamera")) {
                device.turnOn();
                device.setParameter(SecurityCamera.RECORDING_PARAM, "true");
            } else {
                device.turnOff();
            }
        }
        System.out.println("--- Leave Home Mode is active ---");
    }
}