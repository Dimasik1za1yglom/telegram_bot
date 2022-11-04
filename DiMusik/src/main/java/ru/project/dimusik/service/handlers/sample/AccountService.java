package ru.project.dimusik.service.handlers.sample;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface AccountService {

    /**
     * сохранение нового аккаунта в базе данных
     * @param message сообщение нового пользователя
     */
    void saveNewAccount(Message message);


}
