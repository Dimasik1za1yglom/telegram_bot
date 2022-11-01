package ru.project.dimusik.constants;

public enum ConstInfoMenu {
    HELP("Что может данный бот"),
    START("Сообщение с приветствием");

    private final String message;

    ConstInfoMenu(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
