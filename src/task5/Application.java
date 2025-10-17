package task5;

import task5.devices.Device;
import task5.devices.Light;
import task5.devices.MusicSystem;
import task5.devices.SecurityCamera;
import task5.devices.Thermostat;
import task5.decorators.EnergySavingDecorator;
import task5.decorators.RemoteAccessDecorator;
import task5.decorators.VoiceControlDecorator;
import task5.facade.HomeAutomationFacade;

public class Application {
    public static void main(String[] args) {


//        System.out.println("INITIALIZATION");
        Light livingRoomLight = new Light("BED LIGHT");
        MusicSystem mainSpeaker = new MusicSystem("SPEAKER");
        Thermostat thermostat = new Thermostat("THERMOSTAT");
        SecurityCamera frontDoorCamera = new SecurityCamera("FRONT STREET");

        Device decoratedLight = new VoiceControlDecorator(
                new EnergySavingDecorator(livingRoomLight)
        );
        Device decoratedMusicSystem = new RemoteAccessDecorator(
                new VoiceControlDecorator(mainSpeaker)
        );
        Device decoratedThermostat = new EnergySavingDecorator(
                thermostat
        );
        Device decoratedCamera = new RemoteAccessDecorator(
                frontDoorCamera
        );



        HomeAutomationFacade facade = new HomeAutomationFacade(
                decoratedLight,
                decoratedMusicSystem,
                decoratedThermostat,
                decoratedCamera
        );

        facade.startPartyMode();
        System.out.println("Total power in Party Mode: " + facade.getTotalPowerConsumption() + "W");

        facade.activateNightMode();
        System.out.println("Total power in Night Mode: " + facade.getTotalPowerConsumption() + "W");

        facade.leaveHome();
        System.out.println("Total power in Away Mode: " + facade.getTotalPowerConsumption() + "W");

    }
}