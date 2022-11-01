package ru.project.dimusik.controller;

import com.vdurmont.emoji.EmojiParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.project.dimusik.config.BotConfiguration;
import ru.project.dimusik.constants.ConstCommand;
import ru.project.dimusik.constants.ConstInfoMenu;
import ru.project.dimusik.constants.ConstMessages;
import ru.project.dimusik.dao.AccountRepository;
import ru.project.dimusik.destelegram.keyboards.ReplyKeyboardMaker;
import ru.project.dimusik.entities.Account;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TelegramBot extends TelegramLongPollingBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramBot.class);

    private final BotConfiguration botConfiguration;
    private final AccountRepository accountRepository;
    private final ReplyKeyboardMaker replyKeyboardMaker;

    public TelegramBot(BotConfiguration botConfiguration, AccountRepository accountRepository, ReplyKeyboardMaker replyKeyboardMarker) {
        this.botConfiguration = botConfiguration;
        this.accountRepository = accountRepository;
        this.replyKeyboardMaker = replyKeyboardMarker;
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
            case "/help" -> sentMessage(idChat, ConstMessages.HELP_TEXT.getMessage());
            default -> sentMessage(idChat, ConstMessages.NOT_COMMAND.getMessage());
        }
    }

    private void startCommand(Long idChat, String nameUser) {
        String answer = EmojiParser.parseToUnicode(String.format("Привет, %s! :heart_eyes:", nameUser));
        sentMessage(idChat, answer);
    }

    private void sentMessage(Long idChat, String textMessage) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(idChat)
                .text(textMessage)
                .replyMarkup(replyKeyboardMaker.getMainMenuKeyboard())
                .build();
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOGGER.error("Error sending the message: {}", e.getMessage());
        }
    }

    private void createMenu() {
        List<BotCommand> listCommands = new ArrayList<>();
        listCommands.add(new BotCommand(ConstCommand.HELP, ConstInfoMenu.HELP.getMessage()));
        listCommands.add(new BotCommand(ConstCommand.START, ConstInfoMenu.START.getMessage()));
//        listCommands.add(new BotCommand("/search_music", "Поиск композиции для воспроизведения"));
//        listCommands.add(new BotCommand("/play", "Возпроизводить композицю"));
//        listCommands.add(new BotCommand("/pause", "Поставить композициб на паузу"));
//        listCommands.add(new BotCommand("/stop", "Остановить композицию"));
//        listCommands.add(new BotCommand("/view_queue", "Посмотреть очередь"));
//        listCommands.add(new BotCommand("/clear_queue", "Очистить всю очередь"));
//        listCommands.add(new BotCommand("/del_queue", "Удалить из очереди"));
//        listCommands.add(new BotCommand("/my_data", "Посмотреть мои данные, как пользователя"));
//        listCommands.add(new BotCommand("/del_my_data", "Удалить мои данные, как пользователя"));
//        listCommands.add(new BotCommand("/settings", "Изменить настройки"));
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
