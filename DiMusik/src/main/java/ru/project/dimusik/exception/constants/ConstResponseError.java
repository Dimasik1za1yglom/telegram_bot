package ru.project.dimusik.exception.constants;

public enum ConstResponseError {
    ERROR_SEND_MESSAGE("Не удалось обработать сообщение. Я устал. Повторите попытку позже)"),

    ERROR_GLOBAL_MESSAGE("Не удалось отправить сообщение с извинениями. Невозможно отправить любое сообщение");

    private final String message;

    ConstResponseError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
