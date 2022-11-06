package ru.project.dimusik.service.errors.implementation;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.project.dimusik.exception.constants.ConstResponseError;
import ru.project.dimusik.service.errors.sample.ErrorService;
import ru.project.dimusik.service.handlers.implementation.ExternalMenuImpl;
import ru.project.dimusik.service.handlers.sample.ExternalMenu;

@RequiredArgsConstructor
@Service
public class ErrorServiceImpl implements ErrorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorServiceImpl.class);

    private final ExternalMenu externalMenu;

    @Override
    public SendMessage createErrorSendMessage(SendMessage sendMessage) {
        sendMessage.setText(ConstResponseError.ERROR_SEND_MESSAGE.getMessage());
        externalMenu.addMenuKeyboard(sendMessage);
        return sendMessage;
    }
}
