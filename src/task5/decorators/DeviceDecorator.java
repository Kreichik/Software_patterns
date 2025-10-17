package task5.decorators;

import task5.devices.Device;

public abstract class DeviceDecorator implements Device {
    protected Device decoratedDevice;

    public DeviceDecorator(Device decoratedDevice) {
        this.decoratedDevice = decoratedDevice;
    }

    public Device getDecoratedDevice() {
        return decoratedDevice;
    }

    @Override
    public void turnOn() {
        decoratedDevice.turnOn();
    }

    @Override
    public void turnOff() {
        decoratedDevice.turnOff();
    }

    @Override
    public String getStatus() {
        return decoratedDevice.getStatus();
    }

    @Override
    public double getPowerValue() {
        return decoratedDevice.getPowerValue();
    }
}