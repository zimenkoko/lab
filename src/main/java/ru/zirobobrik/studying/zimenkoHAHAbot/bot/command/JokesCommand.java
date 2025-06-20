package ru.zirobobrik.studying.zimenkoHAHAbot.bot.command;

import org.springframework.data.domain.Page;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.zirobobrik.studying.zimenkoHAHAbot.DTO.JokeDto;
import ru.zirobobrik.studying.zimenkoHAHAbot.service.JokeServiceImpl;
import ru.zirobobrik.studying.zimenkoHAHAbot.service.SendBotMessageService;

public class JokesCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final JokeServiceImpl jokeService;
    private static final int PAGE_SIZE = 5;

    public JokesCommand(SendBotMessageService sendBotMessageService, JokeServiceImpl jokeService) {
        this.sendBotMessageService = sendBotMessageService;
        this.jokeService = jokeService;
    }

    @Override
    public void execute(Update update) {
        var message = update.getMessage();
        var chatId = message.getChatId().toString();

        String text = message.getText();
        int page = 0;

        String[] parts = text.trim().split("\\s+");
        if (parts.length > 1) {
            try {
                page = Integer.parseInt(parts[1]) - 1;
                if (page < 0) page = 0;
            } catch (NumberFormatException ignored) {
            }
        }

        Page<JokeDto> jokesPage = jokeService.getJokesPaged(page, PAGE_SIZE);

        if (jokesPage.isEmpty()) {
            sendBotMessageService.sendMessage(chatId, "Анекдотов на этой странице нет.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("📖 Анекдоты — страница ")
                .append(jokesPage.getNumber() + 1)
                .append(" из ")
                .append(jokesPage.getTotalPages())
                .append(":\n\n");

        int number = page * PAGE_SIZE + 1;
        for (JokeDto jokeDto : jokesPage.getContent()) {
            sb.append(number++)
                    .append(". ")
                    .append(jokeDto.getText())
                    .append(" — ")
                    .append(jokeDto.getAuthor())
                    .append("\n\n");
        }

        sendBotMessageService.sendMessage(chatId, sb.toString());
    }
}
