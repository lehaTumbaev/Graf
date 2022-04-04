package com.example.graf;

import com.example.graf.model.Material;
import com.example.graf.repository.RealtimeDatabaseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.stream.Collectors;

public class GrafTelegramBot extends TelegramWebhookBot {

   private String botPath;
   private String botUsername;
   private String botToken;
   @Autowired private RealtimeDatabaseClient db;

    //метод, срабатывающий при обращении к боту
    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        //если сообщение без текста или равно null, ничего не делаем
        if (update.getMessage() == null || !update.getMessage().hasText()) return null;

        String chatId = String.valueOf(update.getMessage().getChatId()); //получаем id чата из сообщения
        String response = generateResponse(update.getMessage().getText()); //генерируем ответ

        return ((new SendMessage(chatId, response))); //отправляем сообщение
    }

    //метод, генерирующий ответ
    private String generateResponse(String request) {
        if (request.startsWith("/start")) //при вводе команды /start просим ввести материал
            return "Введите название стройматериала.";

        if (request.length() < 2) //если запрос слишком короткий, сообщаем об этом
            return "Запрос слишком короткий";

        return findBuildingMaterials(request); //ищем материал
    }

    //метод для поиска материалов
    private String findBuildingMaterials(String request) {
        //получаем данные из базы и проверяем их на null
        List<Material> materials = db.getMaterials();
        if (materials == null)
            return "Не удалось подключится к базе данных";

        //создаем новый список с результатами поиска
        String changedRequest = request.toLowerCase() + " ";
        List<Material> responseMaterials = materials.stream().
                filter(material -> material.getName().toLowerCase().contains(changedRequest)).
                collect(Collectors.toList());
        if (responseMaterials.size() == 0)
            return "По вашему запросу ничего не найдено";

        //создаем ответ и заполняем его
        //если результатов поиска больше 30, берем первые 30
        StringBuilder response;
        if (responseMaterials.size() > 30)
            response = new StringBuilder("Первые 30 результатов поиска:");
        else
            response = new StringBuilder("Результаты поиска: ");

        for (int i = 0; i < responseMaterials.size() && i < 30; i++) {
            response.append("\nНоменклатура: ").append(responseMaterials.get(i).getName()).
                    append("\nЦена: ").append(responseMaterials.get(i).getPrice()).append(" р.\n");
        }

        return response.toString();
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