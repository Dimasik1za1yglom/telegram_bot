package ru.project.dimusik.service.errors.implementation;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.project.dimusik.exception.constants.ConstResponseError;
import ru.project.dimusik.service.errors.sample.ErrorService;

@Service
public class ErrorServiceImpl implements ErrorService {

    @Override
    public SendMessage createErrorSendMessage(SendMessage sendMessage) {
        sendMessage.setText(ConstResponseError.ERROR_SEND_MESSAGE.getMessage());
        return sendMessage;
    }
}
