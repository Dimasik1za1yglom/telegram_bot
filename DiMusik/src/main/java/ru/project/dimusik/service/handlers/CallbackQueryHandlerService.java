package ru.project.dimusik.service.handlers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.project.dimusik.constants.CallbackMusicButton;

@RequiredArgsConstructor
@Service
public class CallbackQueryHandlerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CallbackQueryHandlerService.class);

//    public SendMessage processCallbackQuery(CallbackQuery callbackQuery) {
//        String callbackData = callbackQuery.getData();
//        long messageId = callbackQuery.getMessage().getMessageId();
//        Long chatId = callbackQuery.getMessage().getChatId();
//
//        if (callbackData.equals(CallbackMusicButton.YES_SEARCH_MUSIC.name())) {
//
//        } else if (callbackData.equals(CallbackMusicButton.NO_SEARCH_MUSIC.name())) {
//
//        } else if (callbackData.equals(CallbackMusicButton.EDIT_THE_NAME.name())) {
//
//        }
//    }
}
