package ru.project.dimusik.service.response.implementation;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.project.dimusik.dao.AccountRepository;
import ru.project.dimusik.dao.MessageStepRepository;
import ru.project.dimusik.entities.Account;
import ru.project.dimusik.service.response.sample.AccountService;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountRepository accountRepository;
    private final MessageStepRepository stepRepository;

    @Override
    public boolean saveNewAccount(Message message) {
        Long chatId = message.getChatId();
        if (accountRepository.findAccountsByChatId(chatId).isEmpty()) {
            Chat chat = message.getChat();
            Account account = Account.builder()
                    .firstName(chat.getFirstName())
                    .lastName(chat.getLastName())
                    .userName(chat.getUserName())
                    .chatId(chatId)
                    .registeredAt(LocalDateTime.now())
                    .messageStep(stepRepository.getReferenceById(1L))
                    .build();
            try {
                accountRepository.save(account);
            } catch (Exception e) {
                LOGGER.error("Failed to save a new user: {}", e.getMessage());
                return false;
            }
            LOGGER.info("saving a new chat account to the DataBase: {}", account);
        }
        return true;
    }

    @Override
    public String getStepAccount(Long chatId) {
        Optional<Account> account = accountRepository.findAccountsByChatId(chatId);
        return account.isPresent() ? account.get().getMessageStep().getStep() : "neutral";
    }
}
