package ru.project.dimusik.constants;

public enum ConstMessages {
    HELP_TEXT("Сорян, помощи не будет.\n\n " +
            "Просто напиши /start и не парся!"),
    NOT_COMMAND("Команда пока не поддерживается, сорямба"),
    GREETING("Команда пока не поддерживается, сорямба");

    private final String message;

    ConstMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
