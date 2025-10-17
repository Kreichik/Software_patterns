package task5.decorators;

import task5.devices.Device;

public class VoiceControlDecorator extends DeviceDecorator {
    public VoiceControlDecorator(Device decoratedDevice) {
        super(decoratedDevice);
    }

    @Override
    public void turnOn() {
        System.out.println("Voice Command: Turning on device.");
        super.turnOn();
    }

    @Override
    public void turnOff() {
        System.out.println("Voice Command: Turning off device.");
        super.turnOff();
    }

    @Override
    public String getStatus() {
        return super.getStatus() + " [Voice Controlled]";
    }
}