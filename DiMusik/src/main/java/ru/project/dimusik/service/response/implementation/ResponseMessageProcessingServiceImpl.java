package ru.project.dimusik.service.response.implementation;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.project.dimusik.keyboards.InlineKeyboardMaker;
import ru.project.dimusik.service.response.sample.ResponseMessageProcessingService;

@RequiredArgsConstructor
@Service
public class ResponseMessageProcessingServiceImpl implements ResponseMessageProcessingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseMessageProcessingServiceImpl.class);

    private final InlineKeyboardMaker inlineKeyboardMaker;

    @Override
    public void addInlineKeyboardMarkup(SendMessage message) {
        message.setReplyMarkup(inlineKeyboardMaker.getInlineMessageButtons());
        LOGGER.info("added an inline keyboard to the message");
    }
}
