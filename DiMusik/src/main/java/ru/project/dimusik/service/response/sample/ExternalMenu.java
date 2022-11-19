package ru.project.dimusik.service.response.sample;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public interface ExternalMenu {

    /**
     * создание боковово меню
     * @return лист команд с пояснениями
     */
    List<BotCommand> createMenu();

    /**
     * добавление клавиатурного меню к сообщению
     * @param sendMessage сообщение для пользователя
     */
    void addMenuKeyboard(SendMessage sendMessage);
}
