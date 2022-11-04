package ru.project.dimusik.service.handlers.implementation;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.project.dimusik.dao.AccountRepository;
import ru.project.dimusik.entities.Account;
import ru.project.dimusik.service.handlers.sample.AccountService;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountRepository accountRepository;

    @Override
    public void saveNewAccount(Message message) {
        Long chatId = message.getChatId();
        if (accountRepository.findAccountsByChatId(chatId).isEmpty()) {
            Chat chat = message.getChat();
            Account account = Account.builder()
                    .firstName(chat.getFirstName())
                    .lastName(chat.getLastName())
                    .userName(chat.getUserName())
                    .chatId(chatId)
                    .registeredAt(LocalDateTime.now())
                    .build();
            accountRepository.save(account);
            LOGGER.info("saving a new chat account to the DataBase: {}", account);
        }
    }
}
