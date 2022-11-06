package ru.project.dimusik.service.handlers.sample;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface ResponseMessageProcessingService {

    /**
     * добавление кнопок инлайн-клавиатуры
     */
    void addInlineKeyboardMarkup(SendMessage message);
}
