package ru.zirobobrik.studying.zimenkoHAHAbot.DTO;

public class JokeDto {
    private Long id;
    private String text;
    private String author;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "ID:" + (id != null ? id : "null") + "\n\"" + text + "\"" + (author != null && !author.isEmpty() ? " — " + author : "");
    }
}
