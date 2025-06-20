package ru.zirobobrik.studying.zimenkoHAHAbot.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class JokeCall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "joke_id", nullable = false)
    private Joke joke;

    @Temporal(TemporalType.TIMESTAMP)
    private Date calledAt;

    public JokeCall() {}

    public Long getId() {
        return id;
    }

    public Joke getJoke() {
        return joke;
    }

    public void setJoke(Joke joke) {
        this.joke = joke;
    }

    public Date getCalledAt() {
        return calledAt;
    }

    public void setCalledAt(Date calledAt) {
        this.calledAt = calledAt;
    }
}
