package ru.project.dimusik.service.handlers.implementation;

import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.project.dimusik.constants.ConstMessages;
import ru.project.dimusik.service.handlers.sample.MessageHandlerService;

@RequiredArgsConstructor
@Service
public class MessageHandlerServiceImpl implements MessageHandlerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandlerServiceImpl.class);

    @Override
    public SendMessage messageProcessingHelp(Message message) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(message.getChatId())
                .text(ConstMessages.HELP_TEXT.getMessage()).build();
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
        LOGGER.info("create SendMessage to the command /start");
        return sendMessage;
    }

    @Override
    public SendMessage messageProcessingSearchMusic(Message message) {
        return null;
    }

    @Override
    public SendMessage processingNonExistentCommands(Message message) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(message.getChatId())
                .text(ConstMessages.NOT_COMMAND.getMessage()).build();
        LOGGER.info("create SendMessage to the non existent command");
        return sendMessage;
    }
}
