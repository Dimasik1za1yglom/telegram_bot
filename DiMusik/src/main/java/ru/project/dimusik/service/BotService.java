package ru.project.dimusik.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.project.dimusik.config.BotConfiguration;
import ru.project.dimusik.dao.AccountRepository;
import ru.project.dimusik.entities.Account;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BotService extends TelegramLongPollingBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(BotService.class);
    private static final String HELP_TEXT = "Сорян, помощи не будет.\n\n " +
            "Просто напиши /start и не парся!";

    private final BotConfiguration botConfiguration;
    private final AccountRepository accountRepository;

    public BotService(BotConfiguration botConfiguration, AccountRepository accountRepository) {
        this.botConfiguration = botConfiguration;
        this.accountRepository = accountRepository;
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
        if (update.hasMessage() && update.getMessage().hasText()) {
            respondToCommands(update.getMessage());
        }
    }

    private void respondToCommands(Message message) {
        String textMessage = message.getText();
        Long idChat = message.getChatId();
        LOGGER.info("Received a message: {} - chat id: {}", textMessage, idChat);
        switch (textMessage) {
            case "/start" -> {
                registerAccount(message);
                startCommand(idChat, message.getChat().getUserName());
            }
            case "/help" -> sentMessage(idChat, HELP_TEXT);
            default -> sentMessage(idChat, "Команда пока не поддерживается, сорямба)");
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
            LOGGER.error("Error sending the message: {}", e.getMessage());
        }
    }

    private void createMenu() {
        List<BotCommand> listCommands = new ArrayList<>();
        listCommands.add(new BotCommand("/help", "Что может данный бот"));
        listCommands.add(new BotCommand("/start", "Сообщение с приветствием"));
        listCommands.add(new BotCommand("/search_music", "Поиск композиции для воспроизведения"));
        listCommands.add(new BotCommand("/play", "Возпроизводить композицю"));
        listCommands.add(new BotCommand("/pause", "Поставить композициб на паузу"));
        listCommands.add(new BotCommand("/stop", "Остановить композицию"));
        listCommands.add(new BotCommand("/view_queue", "Посмотреть очередь"));
        listCommands.add(new BotCommand("/clear_queue", "Очистить всю очередь"));
        listCommands.add(new BotCommand("/del_queue", "Удалить из очереди"));
        listCommands.add(new BotCommand("/my_data", "Посмотреть мои данные, как пользователя"));
        listCommands.add(new BotCommand("/del_my_data", "Удалить мои данные, как пользователя"));
        listCommands.add(new BotCommand("/settings", "Изменить настройки"));
        LOGGER.info("create list commands");
        try {
            this.execute(SetMyCommands.builder()
                    .commands(listCommands).build());
        } catch (TelegramApiException e) {
            LOGGER.error("Menu creation error: {}", e.getMessage());
        }
        LOGGER.info("The bot menu has been created");
    }

    private void registerAccount(Message message) {
        Long chatId = message.getChatId();
        if (accountRepository.findAccountsByChatId(chatId).isEmpty()) {
            Chat chat = message.getChat();
            Account account = Account.builder()
                    .firstName(chat.getFirstName())
                    .lastName(chat.getLastName())
                    .userName(chat.getUserName())
                    .chatId(chatId)
                    .registeredAt(LocalDateTime.now())
                    .build();
            accountRepository.save(account);
            LOGGER.info("saving a new chat account: {}", account);
        }
    }
}
