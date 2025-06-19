package ru.zirobobrik.studying.zimenkoHAHAbot.service;

import jakarta.transaction.Transactional;
import ru.zirobobrik.studying.zimenkoHAHAbot.DTO.JokeDto;
import ru.zirobobrik.studying.zimenkoHAHAbot.entity.Joke;
import org.springframework.stereotype.Service;
import ru.zirobobrik.studying.zimenkoHAHAbot.exception.JokeNotFoundException;
import ru.zirobobrik.studying.zimenkoHAHAbot.repository.JokeRepository;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JokeServiceImpl implements JokeService{
    private final JokeRepository jokeRepository;

    public JokeServiceImpl(JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    @Override
    public List<JokeDto> getAllJoke() {
        return jokeRepository.findAll().stream()
                .map(this::convertToJokeDto)
                .collect(Collectors.toList());
    }
    @Transactional
    public void saveJoke(Joke newJoke){
        jokeRepository.save(newJoke);
    }
    public JokeDto getJokeById(Long id) {
        Joke joke = jokeRepository.findById(id).orElseThrow();
        return convertToJokeDto(joke);
    }
    public JokeDto convertToJokeDto(Joke joke) {
        JokeDto jokeDto = new JokeDto();
        jokeDto.setId(joke.getId());
        jokeDto.setText(joke.getText());
        jokeDto.setAuthor(joke.getAuthor());
        return jokeDto;
    }
    @Transactional
    @Override
    public void deleteJokeById(Long id) {
        if (!jokeRepository.existsById(id)) {
            throw new JokeNotFoundException("Анекдот с "+id+" не найден");
        }
        jokeRepository.deleteById(id);
    }
    @Transactional
    public void editJokeById(Long id, String text) {
        Joke joke = jokeRepository.findById(id).orElseThrow(() -> new JokeNotFoundException("Анекдот с "+id+" не найден"));
        joke.setText(text);
        joke.setDateOfModify(new Date());
        jokeRepository.save(joke);
    }
}