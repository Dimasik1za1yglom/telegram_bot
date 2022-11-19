package ru.project.dimusik.keyboards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.project.dimusik.constants.CallbackMusicButton;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class InlineKeyboardMaker {
    private static final Logger LOGGER = LoggerFactory.getLogger(InlineKeyboardMaker.class);

    public InlineKeyboardMarkup getInlineMessageButtons() {

        List<InlineKeyboardButton> keyboardButtonsRow = Arrays.stream(CallbackMusicButton.values())
                .map(x -> getButton(x.getValue(), x.name()))
                .collect(Collectors.toList());

        var rowList = Stream.<List<InlineKeyboardButton>>builder()
                .add(keyboardButtonsRow).build().toList();

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);
        LOGGER.info("create InlineKeyboardMarkup {}", inlineKeyboardMarkup);
        return inlineKeyboardMarkup;
    }

    private InlineKeyboardButton getButton(String buttonName, String buttonCallBackData) {
        InlineKeyboardButton button = InlineKeyboardButton.builder()
                .text(buttonName)
                .callbackData(buttonCallBackData)
                .build();
        LOGGER.info("Creating a button {}", button);
        return button;
    }
}
