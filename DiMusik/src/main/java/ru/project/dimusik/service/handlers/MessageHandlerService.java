package ru.project.dimusik.service.handlers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.project.dimusik.constants.commands.ConstCommandsMenu;
import ru.project.dimusik.service.response.sample.AccountService;
import ru.project.dimusik.service.response.sample.CommandMessageProcessingService;

@RequiredArgsConstructor
@Service
public class MessageHandlerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandlerService.class);

    private final AccountService accountService;
    private final CommandMessageProcessingService messageHandlerService;

    public SendMessage respondToCommands(Message message) {
        String textMessage = message.getText();
        LOGGER.info("Received a Command  message: {}.- chat: {}", textMessage, message.getChat());
        if (textMessage.equals(ConstCommandsMenu.START.getValue())) {
            return accountService.saveNewAccount(message) ? messageHandlerService.messageProcessingStart(message) : null;
        } else if (textMessage.equals(ConstCommandsMenu.HELP.getValue())) {
            return messageHandlerService.messageProcessingHelp(message);
        } else if (textMessage.equals(ConstCommandsMenu.SEARCH.getValue())) {
            return messageHandlerService.messageProcessingSearchMusic(message);
        } else {
            return messageHandlerService.processingNonExistentCommands(message);
        }
    }

    public SendMessage respondToText(Message message) {
        String textMessage = message.getText().trim();
        LOGGER.info("Received a Text  message: {}.- chat: {}", textMessage, message.getChat());
        String step =
    }
}
