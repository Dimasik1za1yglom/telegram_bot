package ru.project.dimusik.constants.commands;

public enum ConstCommandsMenu {
    START("/start"),
    HELP("/help"),
    SEARCH("/search");

    private final String value;

    ConstCommandsMenu(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

