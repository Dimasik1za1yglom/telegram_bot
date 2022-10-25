package ru.project.dimusik.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.project.dimusik.config.BotConfiguration;

@Service
public class BotService extends TelegramLongPollingBot {


    private final BotConfiguration botConfiguration;

    public BotService(BotConfiguration botConfiguration) {
        this.botConfiguration = botConfiguration;
    }

    @Override
    public String getBotUsername() {
        return botConfiguration.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfiguration.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String textMessage = update.getMessage().getText();
            Long idChat = update.getMessage().getChatId();

            switch (textMessage) {
                case "/start" -> startCommand(idChat, update.getMessage().getChat().getUserName());
                default -> sentMessage(idChat, "Команда пока не поддерживается, сорямба)");
            }
        }
    }

    private void startCommand(Long idChat, String nameUser) {
        String answer = String.format("Привет, %s!", nameUser);
        sentMessage(idChat, answer);
    }

    private void sentMessage(Long idChat, String textMessage) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(idChat)
                .text(textMessage)
                .build();
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
