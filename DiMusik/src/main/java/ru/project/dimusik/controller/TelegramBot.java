package ru.project.dimusik.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.project.dimusik.config.BotConfiguration;
import ru.project.dimusik.exception.constants.ConstResponseError;
import ru.project.dimusik.service.errors.sample.ErrorService;
import ru.project.dimusik.service.handlers.CallbackQueryHandlerService;
import ru.project.dimusik.service.handlers.MessageHandlerService;
import ru.project.dimusik.service.response.sample.ExternalMenu;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Controller
public class TelegramBot extends TelegramLongPollingBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramBot.class);

    private final BotConfiguration botConfiguration;
    private final ExternalMenu externalMenu;
    private final ErrorService errorService;
    private final MessageHandlerService messageHandlerService;
    private final CallbackQueryHandlerService callbackQueryHandlerService;

    @PostConstruct
    public void init() {
        createMenu();
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
        if (update.hasCallbackQuery()) {
//            sentMessage(callbackQueryHandlerService.processCallbackQuery(update.getCallbackQuery()));

        } else if (update.hasMessage() && update.getMessage().isCommand()) {
            sentMessage(messageHandlerService.respondToCommands(update.getMessage()));
        } else if (update.hasMessage() && update.getMessage().hasText()) {

        }

    }

    private void sentMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
            LOGGER.info("sent a message to the user: {}", sendMessage);
        } catch (TelegramApiException e) {
            LOGGER.error("Error sending the message: {}", e.getMessage());
            sentErrorMessage(sendMessage);
        }
    }

    private void sentErrorMessage(SendMessage sendMessage) {
        try {
            execute(errorService.createErrorSendMessage(sendMessage));
        } catch (TelegramApiException e) {
            LOGGER.error(" {} Error sending the message: {}", ConstResponseError.ERROR_GLOBAL_MESSAGE, e.getMessage());
        }
    }

    private void createMenu() {
        try {
            execute(SetMyCommands.builder()
                    .commands(externalMenu.createMenu()).build());
            LOGGER.info("The bot menu has been created");
        } catch (TelegramApiException e) {
            LOGGER.error("Menu creation error: {}", e.getMessage());
        }
    }
}
