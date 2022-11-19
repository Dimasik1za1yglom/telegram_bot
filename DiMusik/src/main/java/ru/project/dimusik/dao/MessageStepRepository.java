package ru.project.dimusik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.dimusik.entities.MessageStep;

/*
Взаимодейстиве с шагами общений пользователся с ботом
 */
public interface MessageStepRepository extends JpaRepository<MessageStep, Long> {
}
