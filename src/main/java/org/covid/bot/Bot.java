package org.covid.bot;

import org.covid.command.CommandHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {
    private Map<String, String> countryMap = new HashMap<>();
    private CommandHandler handler = new CommandHandler();
    private static final String BOT_TOKEN = System.getenv("TOKEN");

    public Bot() {
        initializeMap();
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage().setChatId(update.getMessage().getChatId());
        String text = update.getMessage().getText();
        String enCountry = countryMap.get(text);
        if (enCountry == null) {
            handler.command(text, message);
        } else {
            handler.command(enCountry,message);
        }
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "@JBrainCovidBot";
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    private void initializeMap() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("country.csv");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
            while (reader.ready()) {
                String line = reader.readLine();
                countryMap.put(line.split(",")[0],line.split(",")[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
