package ru.zirobobrik.studying.zimenkoHAHAbot.service;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.zirobobrik.studying.zimenkoHAHAbot.DTO.JokeDto;
import ru.zirobobrik.studying.zimenkoHAHAbot.entity.Joke;
import ru.zirobobrik.studying.zimenkoHAHAbot.entity.JokeCall;
import ru.zirobobrik.studying.zimenkoHAHAbot.exception.JokeNotFoundException;
import ru.zirobobrik.studying.zimenkoHAHAbot.repository.JokeCallRepository;
import ru.zirobobrik.studying.zimenkoHAHAbot.repository.JokeRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class JokeServiceImpl implements JokeService {

    private final JokeRepository jokeRepository;
    private final JokeCallRepository jokeCallRepository;

    public JokeServiceImpl(JokeRepository jokeRepository, JokeCallRepository jokeCallRepository) {
        this.jokeRepository = jokeRepository;
        this.jokeCallRepository = jokeCallRepository;
    }

    @Override
    public List<JokeDto> getAllJoke() {
        return jokeRepository.findAll().stream()
                .map(this::convertToJokeDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void saveJoke(Joke newJoke) {
        jokeRepository.save(newJoke);
    }

    @Transactional
    @Override
    public JokeDto getJokeById(Long id) {
        Joke joke = jokeRepository.findById(id).orElseThrow(() -> new JokeNotFoundException("Анекдот с id " + id + " не найден"));

        // Запись вызова
        JokeCall call = new JokeCall();
        call.setJoke(joke);
        call.setCalledAt(new Date());
        jokeCallRepository.save(call);

        return convertToJokeDto(joke);
    }

    @Override
    public List<JokeDto> getTop5PopularJokes() {
        List<Object[]> topJokes = jokeCallRepository.findTopJokes();

        return topJokes.stream()
                .limit(5)
                .map(obj -> {
                    Long jokeId = (Long) obj[0];
                    return jokeRepository.findById(jokeId)
                            .map(this::convertToJokeDto)
                            .orElse(null);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public Page<JokeDto> getJokesPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return jokeRepository.findAll(pageable)
                .map(this::convertToJokeDto);
    }


    @Override
    public JokeDto getRandomJoke() {
        Joke joke = jokeRepository.findRandomJoke();
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
            throw new JokeNotFoundException("Анекдот с " + id + " не найден");
        }
        jokeRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void editJokeById(Long id, String text) {
        Joke joke = jokeRepository.findById(id).orElseThrow(() -> new JokeNotFoundException("Анекдот с " + id + " не найден"));
        joke.setText(text);
        joke.setDateOfModify(new Date());
        jokeRepository.save(joke);
    }
}
