package ru.zirobobrik.studying.zimenkoHAHAbot.bot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.zirobobrik.studying.zimenkoHAHAbot.service.JokeServiceImpl;
import ru.zirobobrik.studying.zimenkoHAHAbot.service.SendBotMessageService;

public class RandomJokeCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final JokeServiceImpl jokeService;

    public RandomJokeCommand(SendBotMessageService sendBotMessageService, JokeServiceImpl jokeService) {
        this.sendBotMessageService = sendBotMessageService;
        this.jokeService = jokeService;
    }

    @Override
    public void execute(Update update) {
        var chatId = update.getMessage().getChatId().toString();
        var jokeDto = jokeService.getRandomJoke();
        String message = (jokeDto != null)
                ? "🎲 Случайный анекдот:\n\n" + jokeDto.getText() + "\n— " + jokeDto.getAuthor()
                : "Анекдоты пока не добавлены.";
        sendBotMessageService.sendMessage(chatId, message);
    }
}

