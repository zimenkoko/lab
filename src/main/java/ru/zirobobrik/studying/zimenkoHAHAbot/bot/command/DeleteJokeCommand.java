package ru.zirobobrik.studying.zimenkoHAHAbot.bot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.zirobobrik.studying.zimenkoHAHAbot.service.JokeServiceImpl;
import ru.zirobobrik.studying.zimenkoHAHAbot.service.SendBotMessageService;

public class DeleteJokeCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final JokeServiceImpl jokeService;

    public DeleteJokeCommand(SendBotMessageService sendBotMessageService, JokeServiceImpl jokeService) {
        this.sendBotMessageService = sendBotMessageService;
        this.jokeService = jokeService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), "Шутка удалена. Она реально не очень.");
        jokeService.deleteJokeById(Long.parseLong(update.getMessage().getText().replace("/delete ", "")));
    }
}