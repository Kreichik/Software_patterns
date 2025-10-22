package com.smarthome.decorators;

import com.smarthome.devices.base.Device;

public class RemoteAccessDecorator extends DeviceDecorator {

    public RemoteAccessDecorator(Device decoratedDevice) {
        super(decoratedDevice);
    }

    @Override
    public void operate() {
        System.out.print("[Remote Access Enabled] -> ");
        super.operate();
    }

    @Override
    public String getName() {
        return super.getName() + " (Remote Access)";
    }
}