package task5.decorators;

import task5.devices.Device;

public class EnergySavingDecorator extends DeviceDecorator {
    private static final double ECO_MINUS = 0.6;

    public EnergySavingDecorator(Device decoratedDevice) {
        super(decoratedDevice);
    }

    @Override
    public void turnOn() {
        super.turnOn();
        activateEnergySaving();
    }

    @Override
    public String getStatus() {
        return super.getStatus() + " [Energy Saving Mode]";
    }

    @Override
    public double getPowerValue() {
        return super.getPowerValue() * ECO_MINUS;
    }

    private void activateEnergySaving() {
        System.out.println("Energy Saving Mode: Activated. Power consumption reduced");
    }
}