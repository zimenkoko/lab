package ru.zirobobrik.studying.zimenkoHAHAbot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<List<JokeDto>> getAllJoke(){
        return ResponseEntity.ok(jokeService.getAllJoke());
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
    public JokeDto getJokeById(@PathVariable Long id){
        return jokeService.getJokeById(id);
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJokeById(@PathVariable Long id){
        jokeService.deleteJokeById(id);
        return ResponseEntity.ok("Анекдот удален");
    }
}