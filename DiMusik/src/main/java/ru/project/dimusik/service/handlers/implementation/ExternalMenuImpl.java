package ru.project.dimusik.service.handlers.implementation;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import ru.project.dimusik.constants.ConstCommand;
import ru.project.dimusik.constants.ConstInfoMenu;
import ru.project.dimusik.destelegram.keyboards.ReplyKeyboardMaker;
import ru.project.dimusik.service.handlers.sample.ExternalMenu;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ExternalMenuImpl implements ExternalMenu {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalMenuImpl.class);

    private final ReplyKeyboardMaker replyKeyboardMaker;

    @Override
    public List<BotCommand> createMenu() {
        List<BotCommand> listCommands = new ArrayList<>();
        listCommands.add(new BotCommand(ConstCommand.START, ConstInfoMenu.START.getMessage()));
        listCommands.add(new BotCommand(ConstCommand.HELP, ConstInfoMenu.HELP.getMessage()));
        LOGGER.info("create list commands");
        return listCommands;
    }

    @Override
    public void addMenuKeyboard(SendMessage sendMessage) {
        sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
        LOGGER.info("added to the salad keyboard menu");
    }
}
