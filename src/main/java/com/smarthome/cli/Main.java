package com.smarthome.cli;

import com.smarthome.bot.SmartHomeBot;
import com.smarthome.decorators.EnergySavingDecorator;
import com.smarthome.decorators.RemoteAccessDecorator;
import com.smarthome.decorators.VoiceControlDecorator;
import com.smarthome.devices.base.Device;
import com.smarthome.devices.concrete.Light;
import com.smarthome.devices.concrete.MusicSystem;
import com.smarthome.devices.concrete.SecurityCamera;
import com.smarthome.devices.concrete.Thermostat;
import com.smarthome.facade.HomeAutomationFacade;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Device> devices = new ArrayList<>();
        Device livingRoomLight = new Light("Bed Light", "Living Room");
        Device mainThermostat = new Thermostat("Thermostat", "Hall");
        Device livingRoomMusic = new MusicSystem("Speakers", "Hall");
        Device frontDoorCamera = new SecurityCamera("Front Door Camera", "yard");
        Device decoratedLivingRoomLight = new VoiceControlDecorator(new EnergySavingDecorator(livingRoomLight));
        Device decoratedMusicSystem = new RemoteAccessDecorator(livingRoomMusic);
        devices.add(decoratedLivingRoomLight);
        devices.add(mainThermostat);
        devices.add(decoratedMusicSystem);
        devices.add(frontDoorCamera);

        HomeAutomationFacade facade = new HomeAutomationFacade(devices);

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new SmartHomeBot(devices, facade));
            System.out.println("Telegram bot started successfully!");
        } catch (TelegramApiException e) {
            System.out.println("Error: " + e.getMessage());
        }

        SmartHomeCLI cli = new SmartHomeCLI(devices, facade);

        cli.run();
    }
}