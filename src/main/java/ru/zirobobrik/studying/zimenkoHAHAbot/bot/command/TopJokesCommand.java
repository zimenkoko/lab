package ru.zirobobrik.studying.zimenkoHAHAbot.bot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.zirobobrik.studying.zimenkoHAHAbot.service.JokeServiceImpl;
import ru.zirobobrik.studying.zimenkoHAHAbot.service.SendBotMessageService;

import java.util.List;

public class TopJokesCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final JokeServiceImpl jokeService;

    public TopJokesCommand(SendBotMessageService sendBotMessageService, JokeServiceImpl jokeService) {
        this.sendBotMessageService = sendBotMessageService;
        this.jokeService = jokeService;
    }

    @Override
    public void execute(Update update) {
        var chatId = update.getMessage().getChatId().toString();
        List<?> topJokes = jokeService.getTop5PopularJokes();

        if (topJokes.isEmpty()) {
            sendBotMessageService.sendMessage(chatId, "Топ анекдотов пока пуст.");
            return;
        }

        StringBuilder sb = new StringBuilder("🔥 Топ-5 популярных анекдотов:\n\n");
        int index = 1;
        for (var joke : topJokes) {
            var j = (ru.zirobobrik.studying.zimenkoHAHAbot.DTO.JokeDto) joke;
            sb.append(index++)
                    .append(". ")
                    .append(j.getText())
                    .append(" — ")
                    .append(j.getAuthor())
                    .append("\n\n");
        }
        sendBotMessageService.sendMessage(chatId, sb.toString());
    }
}
