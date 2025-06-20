package ru.zirobobrik.studying.zimenkoHAHAbot.service;

import ru.zirobobrik.studying.zimenkoHAHAbot.DTO.JokeDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JokeService {
    List<JokeDto> getAllJoke();

    List<JokeDto> getTop5PopularJokes();

    JokeDto getJokeById(Long id);

    void deleteJokeById(Long id);

    void editJokeById(Long id, String text);

    void saveJoke(ru.zirobobrik.studying.zimenkoHAHAbot.entity.Joke newJoke);

    Page<JokeDto> getJokesPaged(int page, int size);

    JokeDto getRandomJoke();
}
