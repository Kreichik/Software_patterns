package com.smarthome.decorators;

import com.smarthome.devices.base.Device;

public class VoiceControlDecorator extends DeviceDecorator {

    public VoiceControlDecorator(Device decoratedDevice) {
        super(decoratedDevice);
    }

    @Override
    public void operate() {
        System.out.print("[Voice Control Enabled] -> ");
        super.operate();
    }

    @Override
    public String getName() {
        return super.getName() + " (Voice Control)";
    }

    @Override
    public void executeVoiceCommand(String command) {
        System.out.println("[Voice Interpretation] -> Processing '" + command + "' for " + decoratedDevice.getName());
        String lowerCaseCommand = command.toLowerCase();

        if (lowerCaseCommand.contains("on")) {
            decoratedDevice.turnOn();
        } else if (lowerCaseCommand.contains("off")) {
            decoratedDevice.turnOff();
        } else if (lowerCaseCommand.startsWith("set")) {
            parseAndSetParameter(lowerCaseCommand);
        } else {
            System.out.println("[Voice Interpretation] -> Unrecognized command: '" + command + "'");
        }
    }

    private void parseAndSetParameter(String command) {
        String[] parts = command.split(" ");
        if (parts.length == 3) {
            String paramKey = parts[1];
            String paramValue = parts[2];

            if (decoratedDevice.getAllParameters().containsKey(paramKey)) {
                decoratedDevice.setParameter(paramKey, paramValue);
            } else {
                System.out.println("[Voice Interpretation] -> Device does not have a parameter named '" + paramKey + "'");
            }
        } else {
            System.out.println("[Voice Interpretation] -> Invalid command");
        }
    }
}