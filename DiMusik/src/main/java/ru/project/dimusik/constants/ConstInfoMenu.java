package ru.project.dimusik.constants;

/**
 * Названия кнопок основной клавиатуры
 */
public enum ConstInfoMenu {
    HELP("Что может данный бот"),
    START("Сообщение с приветствием"),
    SEARCH("Поиск музыки");

    private final String message;

    ConstInfoMenu(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
