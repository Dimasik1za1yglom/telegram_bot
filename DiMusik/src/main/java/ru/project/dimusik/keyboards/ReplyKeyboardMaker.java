package ru.project.dimusik.keyboards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.project.dimusik.constants.commands.ConstCommandsMenu;

import java.util.stream.Stream;

@Component
public class ReplyKeyboardMaker {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReplyKeyboardMaker.class);

    public ReplyKeyboardMarkup getMainMenuKeyboard() {
        KeyboardRow row1 = new KeyboardRow();
        for (ConstCommandsMenu command : ConstCommandsMenu.values()) {
            row1.add(command.getValue());
        }

        var keyboard = Stream.<KeyboardRow>builder()
                .add(row1).build().toList();

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);
        LOGGER.info("created  a menu keyboard");
        return keyboardMarkup;
    }
}
