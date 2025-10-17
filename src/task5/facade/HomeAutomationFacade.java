package task5.facade;

import task5.devices.Device;
import task5.devices.Light;
import task5.devices.MusicSystem;
import task5.devices.Thermostat;


public class HomeAutomationFacade {
    private Device light;
    private Device musicSystem;
    private Device thermostat;
    private Device securityCamera;

    public HomeAutomationFacade(Device light, Device musicSystem, Device thermostat, Device securityCamera) {
        this.light = light;
        this.musicSystem = musicSystem;
        this.thermostat = thermostat;
        this.securityCamera = securityCamera;
    }


    public void Night() {
        System.out.println("\n---- Activating Night Mode...");
        light.turnOff();
        System.out.println("\n");
        musicSystem.turnOff();
        System.out.println("\n");
        thermostat.turnOn();
        System.out.println("\n");
        if (thermostat instanceof Thermostat) {
            ((Thermostat) thermostat).setEcoMode();
        }
        System.out.println("\n");
        securityCamera.turnOn();
        System.out.println("\n");
    }

    public void Party() {
        System.out.println("\n---- Starting Party Mode...");
        light.turnOn();
        System.out.println("\n");
        if (light instanceof Light) {
            ((Light) light).setDimEffect(50);
        }
        System.out.println("\n");
        musicSystem.turnOn();
        System.out.println("\n");
        if (musicSystem instanceof MusicSystem) {
            ((MusicSystem) musicSystem).setVolume(100);
        }
        System.out.println("\n");
        thermostat.turnOn();
        System.out.println("\n");
        if (thermostat instanceof Thermostat){
            ((Thermostat) thermostat).setTemperature(19);
        }
        System.out.println("\n");
        securityCamera.turnOff();
        System.out.println("\n");
    }

    public void home_alone() {
        System.out.println("\n---- Activting Away Mode...");
        light.turnOff();
        System.out.println("\n");
        musicSystem.turnOff();
        System.out.println("\n");
        thermostat.turnOff();
        System.out.println("\n");
        securityCamera.turnOn();
        System.out.println("\n");
    }

    public double getTotalPowerConsumption() {
        return light.getPowerValue() +
                musicSystem.getPowerValue() +
                thermostat.getPowerValue() +
                securityCamera.getPowerValue();
    }


}