package ru.zirobobrik.studying.zimenkoHAHAbot.bot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.zirobobrik.studying.zimenkoHAHAbot.service.SendBotMessageService;
import ru.zirobobrik.studying.zimenkoHAHAbot.service.JokeServiceImpl;
import java.util.stream.Collectors;

public class JokesCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final JokeServiceImpl jokeService;

    public JokesCommand(SendBotMessageService sendBotMessageService, JokeServiceImpl jokeService) {
        this.sendBotMessageService = sendBotMessageService;
        this.jokeService = jokeService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(
                update.getMessage().getChatId().toString(),
                jokeService.getAllJoke().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining("\n"))
        );
    }
}