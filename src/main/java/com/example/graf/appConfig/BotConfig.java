package com.example.graf.appConfig;

import com.example.graf.GrafTelegramBot;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {
    private String botPath;
    private String botUsername;
    private String botToken;

    @Bean
    public GrafTelegramBot grafTelegramBot() {

        GrafTelegramBot grafTelegramBot = new GrafTelegramBot();
        grafTelegramBot.setBotPath(botPath);
        grafTelegramBot.setBotToken(botToken);
        grafTelegramBot.setBotUsername(botUsername);

        return grafTelegramBot;
    }

    public String getBotPath() {
        return botPath;
    }

    public String getBotUsername() {
        return botUsername;
    }

    public String getBotToken() {
        return botToken;
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
