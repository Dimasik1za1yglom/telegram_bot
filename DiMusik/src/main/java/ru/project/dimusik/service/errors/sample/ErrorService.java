package ru.project.dimusik.service.errors.sample;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface ErrorService {

    /**
     * Создает ответное сообщение пользователю при ошибке отправки прошлого сообщения
     * @param sendMessage Cообщение которое нужно исправить
     * @return сообщение с извинениями
     */
    SendMessage createErrorSendMessage(SendMessage sendMessage);
}
