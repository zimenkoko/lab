package ru.zirobobrik.studying.zimenkoHAHAbot.bot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.zirobobrik.studying.zimenkoHAHAbot.entity.Joke;
import ru.zirobobrik.studying.zimenkoHAHAbot.service.JokeServiceImpl;
import ru.zirobobrik.studying.zimenkoHAHAbot.service.SendBotMessageService;

import java.util.Date;

public class CreateJokeCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final JokeServiceImpl jokeService;

    public CreateJokeCommand(SendBotMessageService sendBotMessageService, JokeServiceImpl jokeService) {
        this.sendBotMessageService = sendBotMessageService;
        this.jokeService = jokeService;
    }

    @Override
    public void execute(Update update) {
        if(update.getMessage().getText().contains("/create ") && update.getMessage().getText().replace("/create ", "") != "") {
            sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), "Новая шутка добавлена. Очень смешно.");
            Joke newJoke = new Joke();
            newJoke.setAuthor(update.getMessage().getFrom().getUserName());
            newJoke.setDateOfCreate(new Date());
            newJoke.setDateOfModify(new Date());
            newJoke.setText(update.getMessage().getText().replace("/create ", "").toString());
            newJoke.setId(632L);
            jokeService.saveJoke(newJoke);
        }
    }
}