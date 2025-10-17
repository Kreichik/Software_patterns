package task5.decorators;

import task5.devices.Device;

public class RemoteAccessDecorator extends DeviceDecorator {
    public RemoteAccessDecorator(Device decoratedDevice) {
        super(decoratedDevice);
    }

    @Override
    public void turnOn() {
        authenticateRemote();
        super.turnOn();
    }

    @Override
    public void turnOff() {
        super.turnOff();
        System.out.println("Remote Access: Connection closed");
    }

    @Override
    public String getStatus() {
        return super.getStatus() + " [Remotely Accessible]";
    }

    private void authenticateRemote() {
        System.out.println("Remote Access: Start connection...");
    }
}