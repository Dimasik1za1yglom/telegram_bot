package ru.project.dimusik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.dimusik.entities.Music;

/*
Взаимодейстиве с названиями песен пользователей
 */
public interface MusicRepository extends JpaRepository<Music, Long> {
}
