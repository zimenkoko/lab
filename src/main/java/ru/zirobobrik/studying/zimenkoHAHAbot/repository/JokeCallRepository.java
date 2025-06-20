package ru.zirobobrik.studying.zimenkoHAHAbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.zirobobrik.studying.zimenkoHAHAbot.entity.JokeCall;

import java.util.List;

public interface JokeCallRepository extends JpaRepository<JokeCall, Long> {

    @Query("SELECT jc.joke.id, COUNT(jc.joke.id) " +
            "FROM JokeCall jc GROUP BY jc.joke.id ORDER BY COUNT(jc.joke.id) DESC")
    List<Object[]> findTopJokes();
}
