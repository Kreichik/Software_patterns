package com.smarthome.cli;

import com.smarthome.devices.base.Device;
import com.smarthome.devices.concrete.*;
import com.smarthome.devices.concrete.*;
import com.smarthome.facade.HomeAutomationFacade;

import java.util.List;
import java.util.Scanner;

public class SmartHomeCLI {
    private final Scanner scanner;
    private final List<Device> devices;
    private final HomeAutomationFacade facade;

    public SmartHomeCLI(List<Device> initialDevices, HomeAutomationFacade facade) {
        this.scanner = new Scanner(System.in);
        this.devices = initialDevices;
        this.facade = facade;
    }

    public void run() {
        while (true) {
            printMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    addNewDevice();
                    break;
                case 2:
                    listAllDevices();
                    break;
                case 3:
                    editDevice();
                    break;
                case 4:
                    activateMode();
                    break;
                case 5:
                    useVoiceCommand();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n===== Control Panel =====");
        System.out.println("1. Add New Device");
        System.out.println("2. View All Devices");
        System.out.println("3. Edit Device Parameters");
        System.out.println("4. Activate a Home Mode");
        System.out.println("5. Use Voice Command");
        System.out.println("0. Exit");
        System.out.print("Enter number: ");
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void useVoiceCommand() {
        System.out.println("\n--- Voice Command Interface ---");
        System.out.print("Enter command: ");
        String fullCommand = scanner.nextLine().toLowerCase();

        Device targetDevice = null;
        int bestMatchLength = 0;

        for (Device device : devices) {
            // ИЗМЕНЕНИЕ ЗДЕСЬ: используем getBaseName() вместо getName()
            String deviceName = device.getBaseName().toLowerCase();
            if (fullCommand.startsWith(deviceName) && deviceName.length() > bestMatchLength) {
                targetDevice = device;
                bestMatchLength = deviceName.length();
            }
        }

        if (targetDevice != null) {
            String command = fullCommand.substring(bestMatchLength).trim();
            if(command.isEmpty()) {
                System.out.println("No command provided for device '" + targetDevice.getName() + "'");
                return;
            }
            targetDevice.executeVoiceCommand(command);
        } else {
            System.out.println("Could not find a device matching your command.");
        }
    }

    private void addNewDevice() {
        System.out.println("\n--- Add New Device ---");
        System.out.println("Select device type:");
        System.out.println("1. Light");
        System.out.println("2. Music System");
        System.out.println("3. Thermostat");
        System.out.println("4. Security Camera");
        System.out.println("5. Other Device");
        System.out.print("Enter type: ");
        int type = getUserChoice();

        System.out.print("Enter device name: ");
        String name = scanner.nextLine();
        System.out.print("Enter device room: ");

        String location = scanner.nextLine();

        Device newDevice = null;
        switch (type) {
            case 1: newDevice = new Light(name, location); break;
            case 2: newDevice = new MusicSystem(name, location); break;
            case 3: newDevice = new Thermostat(name, location); break;
            case 4: newDevice = new SecurityCamera(name, location); break;
            case 5:
                System.out.print("Enter base power consumption: ");
                double power = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter custom parameter name: ");
                String paramKey = scanner.nextLine();
                System.out.print("Enter custom parameter value: ");
                String paramValue = scanner.nextLine();
                newDevice = new GenericDevice(name, power, location, paramKey, paramValue);
                break;
            default: System.out.println("Invalid device type"); return;
        }

        if (newDevice != null) {
            devices.add(newDevice);
            System.out.println("Device '" + name + "' added successfully");
        }
    }

    private void listAllDevices() {
        System.out.println("\n--- Devices ---");
        if (devices.isEmpty()) {
            System.out.println("No devices");
            return;
        }
        for (int i = 0; i < devices.size(); i++) {
            Device device = devices.get(i);
            System.out.printf("%d. %s - %s%n", i + 1, device.getDeviceType(), device.getName());
            System.out.println(device.getLocation());
            System.out.printf("INFO: %s, Power Consumption: %.2f W%n", device.getStatus(), device.getPowerConsumption());
            System.out.println("Values: " + device.getAllParameters());
        }
    }

    private void editDevice() {
        System.out.println("\n--- Edit Value ---");
        listAllDevices();
        if (devices.isEmpty()) return;

        System.out.print("Enter the number of the device to edit: ");
        int choice = getUserChoice() - 1;



        Device deviceToEdit = devices.get(choice);
        System.out.println("Editing: " + deviceToEdit.getName());
        System.out.println("Available parameters: " + deviceToEdit.getAllParameters().keySet());

        System.out.print("Enter parameter name to change: ");
        String key = scanner.nextLine();

        System.out.print("Enter new value for '" + key + "': ");
        String value = scanner.nextLine();

        deviceToEdit.setParameter(key, value);
    }

    private void activateMode() {
        System.out.println("\n--- Activate Mode ---");
        System.out.println("1. Party Mode");
        System.out.println("2. Night Mode");
        System.out.println("3. Leave Home");
        System.out.print("Choose mode to activate: ");
        int choice = getUserChoice();

        switch (choice) {
            case 1: facade.startPartyMode(); break;
            case 2: facade.activateNightMode(); break;
            case 3: facade.leaveHome(); break;
            default: System.out.println("Invalid mode choice");
        }
    }
}