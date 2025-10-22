package com.smarthome.decorators;

import com.smarthome.devices.base.Device;

public class EnergySavingDecorator extends DeviceDecorator {
    private static final double SAVING_FACTOR = 0.75;

    public EnergySavingDecorator(Device decoratedDevice) {
        super(decoratedDevice);
    }

    @Override
    public void operate() {
        System.out.print("[Energy Saving Mode Active] -> ");
        super.operate();
    }

    @Override
    public double getPowerConsumption() {
        return super.getPowerConsumption() * SAVING_FACTOR;
    }

    @Override
    public String getName() {
        return super.getName() + " (Energy Saving)";
    }

    @Override
    public String getLocation() {
        return super.getLocation();
    }
}