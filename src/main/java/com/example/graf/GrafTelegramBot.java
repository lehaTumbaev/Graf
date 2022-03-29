package com.example.graf;

import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class GrafTelegramBot extends TelegramWebhookBot {

   private String botPath;
   private String botUsername;
   private String botToken;

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.getMessage() != null && update.getMessage().hasText()) {
            String chat_id = String.valueOf(update.getMessage().getChatId());

            try{
                execute(new SendMessage(chat_id, "Hi " + update.getMessage().getText()));
            }catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return botPath;
    }

    public void setBotPath(String botPath) {
        this.botPath = botPath;
    }

    public void setBotUsername(String botUsername) {
        this.botUsername = botUsername;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }
}
