package ru.k0r0tk0ff.BotEngine;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

public class BotEngine extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new BotEngine());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    //public String getBotUsername() {
    //    return "ИМЯ_ПОЛЬЗОВАТЕЛЯ_ВАШЕГО_БОТА";
    //}
    public String getBotUsername() {
        return "t.me/aaafff_bot";
    }

    @Override
    public String getBotToken() {
        return "369954871:AAHPMnpZaaKrpGPsG4ODlHtowMMXTJVtZK0";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            if (message.getText().equals("/help"))
                sendMsg(message, "Привет, я робот");
            else if (message.getText().equals("who are you?"))
                sendMsg(message, "Hi, i am KODYA!");
            else
                sendMsg(message, "Я не знаю что ответить на это");
        }
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}