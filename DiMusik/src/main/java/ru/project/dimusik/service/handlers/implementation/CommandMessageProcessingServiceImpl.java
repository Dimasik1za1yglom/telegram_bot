package ru.project.dimusik.service.handlers.implementation;

import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.project.dimusik.constants.ConstMessages;
import ru.project.dimusik.service.handlers.sample.CommandMessageProcessingService;
import ru.project.dimusik.service.handlers.sample.ExternalMenu;
import ru.project.dimusik.service.handlers.sample.ResponseMessageProcessingService;

@RequiredArgsConstructor
@Service
public class CommandMessageProcessingServiceImpl implements CommandMessageProcessingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandMessageProcessingServiceImpl.class);

    private final ResponseMessageProcessingService responseMessageProcessingService;
    private final ExternalMenu externalMenu;

    @Override
    public SendMessage messageProcessingHelp(Message message) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(message.getChatId())
                .text(ConstMessages.HELP_TEXT.getMessage()).build();
        externalMenu.addMenuKeyboard(sendMessage);
        LOGGER.info("create SendMessage to the command /help");
        return sendMessage;
    }

    @Override
    public SendMessage messageProcessingStart(Message message) {
        String answer = EmojiParser.parseToUnicode(String.format(ConstMessages.GREETING.getMessage(),
                message.getChat().getUserName()));
        SendMessage sendMessage = SendMessage.builder()
                .chatId(message.getChatId())
                .text(answer).build();
        externalMenu.addMenuKeyboard(sendMessage);
        LOGGER.info("create SendMessage to the command /start");
        return sendMessage;
    }

    @Override
    public SendMessage messageProcessingSearchMusic(Message message) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(message.getChatId())
                .text(ConstMessages.SEARCH_CONFIRMATION.getMessage())
                .build();
        responseMessageProcessingService.addInlineKeyboardMarkup(sendMessage);
        LOGGER.info("create SendMessage to the command /search");
        return sendMessage;
    }

    @Override
    public SendMessage processingNonExistentCommands(Message message) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(message.getChatId())
                .text(ConstMessages.NOT_COMMAND.getMessage()).build();
        externalMenu.addMenuKeyboard(sendMessage);
        LOGGER.info("create SendMessage to the non existent command");
        return sendMessage;
    }
}
