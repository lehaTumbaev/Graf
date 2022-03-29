package com.example.graf.controller;

import com.example.graf.GrafTelegramBot;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebHookController {
    private final GrafTelegramBot grafTelegramBot;

    public WebHookController(GrafTelegramBot grafTelegramBot) {
        this.grafTelegramBot = grafTelegramBot;
    }

    @PostMapping("/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update){
        return grafTelegramBot.onWebhookUpdateReceived(update);
    }
}
