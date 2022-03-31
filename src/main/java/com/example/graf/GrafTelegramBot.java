package com.example.graf;

import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class GrafTelegramBot extends TelegramWebhookBot {

   private String botPath;
   private String botUsername;
   private String botToken;

   //метод, срабатывающий при обращении к боту
    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        //если сообщение без текста или равно null, ничего не делаем
        if (update.getMessage() == null || !update.getMessage().hasText()) return null;

        String chatId = String.valueOf(update.getMessage().getChatId()); //получаем id чата из сообщения

        //при вводе команды /start просим ввести материал
        if (update.getMessage().getText().startsWith("/start"))
            return ((new SendMessage(chatId, "Введите название стройматериала.")));




        return ((new SendMessage(chatId, findBuildingMaterials()))); //вызываем метод поиска материалов
    }

    //метод для поиска материалов
    private String findBuildingMaterials() {
        return "куку";
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
