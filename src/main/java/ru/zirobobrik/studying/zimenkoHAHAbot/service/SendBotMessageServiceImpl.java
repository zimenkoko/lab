package ru.zirobobrik.studying.zimenkoHAHAbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.zirobobrik.studying.zimenkoHAHAbot.bot.JokeBot;
@Service
public class SendBotMessageServiceImpl implements SendBotMessageService{
    private final JokeBot jokeBot;

    public SendBotMessageServiceImpl(JokeBot jokeBot) {
        this.jokeBot = jokeBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try {
            jokeBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            //todo add logging to the project.
            e.printStackTrace();
        }
    }
}