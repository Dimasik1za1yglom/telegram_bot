package ru.project.dimusik.service.handlers.sample;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessageHandlerService {

    /**
     * Обработка сообщения "/help"
     * @param message сообщение пользоваателя
     * @return сообщение ответа
     */
    SendMessage messageProcessingHelp(Message message);

    /**
     * Обработка сообщения "/start"
     * @param message сообщение пользоваателя
     * @return сообщение ответа
     */
    SendMessage messageProcessingStart(Message message);


    /**
     * Обработка сообщения "/search_music"
     * @param message сообщение пользоваателя
     * @return сообщение ответа
     */
    SendMessage messageProcessingSearchMusic(Message message);

    /**
     * Обработка несуществующих команд
     * @param message сообщение пользователя
     * @return сообщение ответа
     */
    SendMessage processingNonExistentCommands(Message message);
}
