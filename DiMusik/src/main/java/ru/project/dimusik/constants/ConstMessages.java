package ru.project.dimusik.constants;

/**
 * Текстовые сообщения, посылаемые ботом
 */
public enum ConstMessages {
    HELP_TEXT("Сорян, помощи не будет.\n\n " +
            "Просто напиши /start и не парся!"),
    NOT_COMMAND("Команда пока не поддерживается, сорямба"),
    GREETING("Привет, %s! :heart_eyes:"),
    SEARCH_CONFIRMATION("Вы уверены в правильности написания названия?");

    private final String message;

    ConstMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
