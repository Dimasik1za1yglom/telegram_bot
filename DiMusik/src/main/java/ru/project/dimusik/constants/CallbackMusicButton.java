package ru.project.dimusik.constants;

/**
 * Элементы ответов кнопок инлайн-клавиатур
 */
public enum CallbackMusicButton {
    YES_SEARCH_MUSIC("Да"),
    NO_SEARCH_MUSIC("Нет"),
    EDIT_THE_NAME("Изменить");

    private final String value;

    CallbackMusicButton(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
