package org.covid.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class CommandHandler {
    private CommandExecutor executor = new CommandExecutor();

    public void command(String msg, SendMessage message) {
        if (msg.equalsIgnoreCase("/start")) {
            message.setText("Введите название страны для просмотра информации по заболеваниям");
        } else {
            message.setText(executor.infoByName(msg));
        }

    }
}
