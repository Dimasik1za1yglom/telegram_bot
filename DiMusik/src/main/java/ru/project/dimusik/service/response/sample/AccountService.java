package ru.project.dimusik.service.response.sample;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface AccountService {

    /**
     * сохранение нового аккаунта в базе данных
     * @param message сообщение нового пользователя
     */
    boolean saveNewAccount(Message message);


    /**
     * получение шага на котором нахоится пользователь
     * @param chatId id чата
     * @return ша в виде строки
     */
    String getStepAccount(Long chatId);
}
