package ru.zirobobrik.studying.zimenkoHAHAbot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import ru.zirobobrik.studying.zimenkoHAHAbot.DTO.JokeDto;
import ru.zirobobrik.studying.zimenkoHAHAbot.entity.Joke;
import ru.zirobobrik.studying.zimenkoHAHAbot.service.JokeServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/joke")
public class JokeController {

    private final JokeServiceImpl jokeService;

    public JokeController(JokeServiceImpl jokeService) {
        this.jokeService = jokeService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<String> createJoke(@RequestBody JokeDto jokeDto) {
        Joke joke = new Joke();
        joke.setText(jokeDto.getText());
        joke.setAuthor(jokeDto.getAuthor());
        jokeService.saveJoke(joke);
        return ResponseEntity.status(HttpStatus.CREATED).body("Анекдот создан");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public ResponseEntity<List<JokeDto>> getAllJoke() {
        return ResponseEntity.ok(jokeService.getAllJoke());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/paged")
    public ResponseEntity<Page<JokeDto>> getJokesPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<JokeDto> jokesPage = jokeService.getJokesPaged(page, size);
        return ResponseEntity.ok(jokesPage);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/random")
    public ResponseEntity<JokeDto> getRandomJoke() {
        return ResponseEntity.ok(jokeService.getRandomJoke());
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ResponseEntity<String> editJokes(
            @PathVariable("id") Long id,
            @RequestBody Joke joke) {
        jokeService.editJokeById(id, joke.getText());
        return ResponseEntity.ok("Анекдот отредактирован");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public JokeDto getJokeById(@PathVariable Long id) {
        return jokeService.getJokeById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJokeById(@PathVariable Long id) {
        jokeService.deleteJokeById(id);
        return ResponseEntity.ok("Анекдот удален");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/top")
    public List<JokeDto> getTopJokes() {
        return jokeService.getTop5PopularJokes();
    }
}
