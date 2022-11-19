package ru.project.dimusik.service.response.implementation;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import ru.project.dimusik.constants.commands.ConstCommandsMenu;
import ru.project.dimusik.constants.commands.ConstInfoMenu;
import ru.project.dimusik.keyboards.ReplyKeyboardMaker;
import ru.project.dimusik.service.response.sample.ExternalMenu;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class ExternalMenuImpl implements ExternalMenu {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalMenuImpl.class);

    private final ReplyKeyboardMaker replyKeyboardMaker;

    @Override
    public List<BotCommand> createMenu() {
        var listCommands = Stream.<BotCommand>builder()
                .add(new BotCommand(ConstCommandsMenu.START.getValue(), ConstInfoMenu.START.getMessage()))
                .add(new BotCommand(ConstCommandsMenu.HELP.getValue(), ConstInfoMenu.HELP.getMessage()))
                .add(new BotCommand(ConstCommandsMenu.SEARCH.getValue(), ConstInfoMenu.SEARCH.getMessage()))
                .build().toList();
        LOGGER.info("create list commands");
        return listCommands;
    }

    @Override
    public void addMenuKeyboard(SendMessage sendMessage) {
        sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
        LOGGER.info("added to the salad keyboard menu");
    }
}
