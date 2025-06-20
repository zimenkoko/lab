package ru.zirobobrik.studying.zimenkoHAHAbot.bot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.zirobobrik.studying.zimenkoHAHAbot.DTO.JokeDto;
import ru.zirobobrik.studying.zimenkoHAHAbot.exception.JokeNotFoundException;
import ru.zirobobrik.studying.zimenkoHAHAbot.service.JokeServiceImpl;
import ru.zirobobrik.studying.zimenkoHAHAbot.service.SendBotMessageService;

public class JokeCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final JokeServiceImpl jokeService;

    public JokeCommand(SendBotMessageService sendBotMessageService, JokeServiceImpl jokeService) {
        this.sendBotMessageService = sendBotMessageService;
        this.jokeService = jokeService;
    }

    @Override
    public void execute(Update update) {
        var message = update.getMessage();
        var chatId = message.getChatId().toString();

        String[] parts = message.getText().trim().split("\\s+");

        if (parts.length != 2) {
            sendBotMessageService.sendMessage(chatId, "❗ Используйте формат команды:\n/joke {id}, где {id} — номер анекдота.");
            return;
        }

        try {
            Long id = Long.parseLong(parts[1]);
            JokeDto jokeDto = jokeService.getJokeById(id);

            String author = (jokeDto.getAuthor() != null && !jokeDto.getAuthor().isBlank())
                    ? " — " + jokeDto.getAuthor()
                    : "";

            String response = String.format("📌 Анекдот #%d:\n\"%s\"%s",
                    jokeDto.getId(), jokeDto.getText(), author);

            sendBotMessageService.sendMessage(chatId, response);
        } catch (NumberFormatException e) {
            sendBotMessageService.sendMessage(chatId, "❗ ID должен быть числом.");
        } catch (JokeNotFoundException e) {
            sendBotMessageService.sendMessage(chatId, "❗ " + e.getMessage());
        } catch (Exception e) {
            sendBotMessageService.sendMessage(chatId, "❗ Ошибка при получении анекдота.");
        }
    }

}
