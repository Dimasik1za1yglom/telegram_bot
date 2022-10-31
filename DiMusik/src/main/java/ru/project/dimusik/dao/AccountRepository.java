package ru.project.dimusik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.dimusik.entities.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findAccountsByChatId(Long chatId);
}
