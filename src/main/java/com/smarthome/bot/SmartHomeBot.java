package com.smarthome.bot;

import com.smarthome.devices.base.Device;
import com.smarthome.facade.HomeAutomationFacade;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmartHomeBot extends TelegramLongPollingBot {

    private final List<Device> devices;
    private final String botUsername;
    private final HomeAutomationFacade facade;
    private final Map<Long, String> userState = new HashMap<>();

    public SmartHomeBot(List<Device> devices, HomeAutomationFacade facade) {
        super("TOKEN");
        this.botUsername = "BOT_USERNAME";
        this.devices = devices;
        this.facade = facade;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            handleTextMessage(update);
        } else if (update.hasCallbackQuery()) {
            handleCallbackQuery(update);
        }
    }

    private void handleTextMessage(Update update) {
        String text = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        String state = userState.get(chatId);
        if (state != null) {
            processState(chatId, text, state);
            return;
        }

        if ("/start".equals(text)) {
            sendMainMenu(chatId);
        } else if ("Devices".equals(text)) {
            sendDeviceList(chatId);
        } else if ("Party Mode".equals(text)) {
            facade.startPartyMode();
            sendMessage(chatId, "Party Mode Activated!");
        } else if ("Night Mode".equals(text)) {
            facade.activateNightMode();
            sendMessage(chatId, "Night Mode Activated!");
        } else if ("Leave Home".equals(text)) {
            facade.leaveHome();
            sendMessage(chatId, "Leave Home Mode Activated!");
        }
    }

    private void sendMainMenu(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Welcome to your Smart Home! Select an option from the menu below.");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("Devices"));
        keyboard.add(row1);

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton("Party Mode"));
        row2.add(new KeyboardButton("Night Mode"));
        row2.add(new KeyboardButton("Leave Home"));
        keyboard.add(row2);

        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        message.setReplyMarkup(keyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void handleCallbackQuery(Update update) {
        String callbackData = update.getCallbackQuery().getData();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        int messageId = update.getCallbackQuery().getMessage().getMessageId();

        String[] parts = callbackData.split("_");
        String action = parts[0];
        int deviceIndex = Integer.parseInt(parts[1]);
        Device device = devices.get(deviceIndex);

        if ("set".equals(action)) {
            sendParameterList(chatId, messageId, deviceIndex);
        } else if ("param".equals(action)) {
            String paramKey = parts[2];
            userState.put(chatId, "setparam_" + deviceIndex + "_" + paramKey);
            sendMessage(chatId, "Enter new value for '" + paramKey + "':");
        } else if ("back".equals(action)) {
            sendDeviceListAsEdit(chatId, messageId);
        } else if ("toggle".equals(action)) {
            String command = parts[2];
            if (command.equals("on")) {
                device.turnOn();
            } else {
                device.turnOff();
            }
            sendDeviceListAsEdit(chatId, messageId);
        }
    }

    private void processState(long chatId, String value, String state) {
        String[] parts = state.split("_");
        int deviceIndex = Integer.parseInt(parts[1]);
        String paramKey = parts[2];

        Device device = devices.get(deviceIndex);
        device.setParameter(paramKey, value);

        sendMessage(chatId, "Parameter '" + paramKey + "' for device '" + device.getName() + "' has been set to '" + value + "'.");
        userState.remove(chatId);
        sendDeviceList(chatId);
    }

    private void sendDeviceList(long chatId) {
        SendMessage message = buildDeviceListMessage(chatId);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendDeviceListAsEdit(long chatId, int messageId) {
        SendMessage baseMessage = buildDeviceListMessage(chatId);

        EditMessageText message = new EditMessageText();
        message.setChatId(String.valueOf(chatId));
        message.setMessageId(messageId);
        message.setText(baseMessage.getText());
        message.setReplyMarkup((InlineKeyboardMarkup) baseMessage.getReplyMarkup());
        message.setParseMode("Markdown");

        try {
            execute(message);
        } catch (TelegramApiException e) {
            if (!e.getMessage().contains("message is not modified")) {
                e.printStackTrace();
            }
        }
    }

    private SendMessage buildDeviceListMessage(long chatId) {
        StringBuilder sb = new StringBuilder("Your devices:\n\n");
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        for (int i = 0; i < devices.size(); i++) {
            Device device = devices.get(i);
            sb.append(String.format("*%s* (%s)\n", device.getName(), device.getDeviceType()));
            sb.append(String.format("Status: %s\n", device.getStatus()));
            sb.append(String.format("Power: %.2f W\n", device.getPowerConsumption()));
            sb.append(String.format("Parameters: %s\n\n", device.getAllParameters().toString()));

            List<InlineKeyboardButton> controlRow = new ArrayList<>();
            InlineKeyboardButton onButton = new InlineKeyboardButton();
            onButton.setText("ON");
            onButton.setCallbackData("toggle_" + i + "_on");
            controlRow.add(onButton);

            InlineKeyboardButton offButton = new InlineKeyboardButton();
            offButton.setText("OFF");
            offButton.setCallbackData("toggle_" + i + "_off");
            controlRow.add(offButton);
            rows.add(controlRow);

            List<InlineKeyboardButton> paramRow = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText("Set Parameter for " + device.getName());
            button.setCallbackData("set_" + i);
            paramRow.add(button);
            rows.add(paramRow);
        }

        keyboard.setKeyboard(rows);
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(sb.toString());
        message.setReplyMarkup(keyboard);
        message.setParseMode("Markdown");

        return message;
    }

    private void sendParameterList(long chatId, int messageId, int deviceIndex) {
        Device device = devices.get(deviceIndex);
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        for (String paramKey : device.getAllParameters().keySet()) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText("Change '" + paramKey + "'");
            button.setCallbackData("param_" + deviceIndex + "_" + paramKey);
            row.add(button);
            rows.add(row);
        }

        List<InlineKeyboardButton> backRow = new ArrayList<>();
        InlineKeyboardButton backButton = new InlineKeyboardButton();
        backButton.setText("<< Back to Devices");
        backButton.setCallbackData("back_" + deviceIndex);
        backRow.add(backButton);
        rows.add(backRow);

        keyboard.setKeyboard(rows);
        EditMessageText message = new EditMessageText();
        message.setChatId(String.valueOf(chatId));
        message.setMessageId(messageId);
        message.setText("Select a parameter to change for *" + device.getName() + "*:");
        message.setReplyMarkup(keyboard);
        message.setParseMode("Markdown");

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }
}