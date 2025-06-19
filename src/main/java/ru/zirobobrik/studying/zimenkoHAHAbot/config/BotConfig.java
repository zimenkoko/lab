package ru.zirobobrik.studying.zimenkoHAHAbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.zirobobrik.studying.zimenkoHAHAbot.bot.JokeBot;

@Configuration
public class BotConfig {

    @Bean
    public TelegramBotsApi telegramBotsApi(JokeBot jokeBot) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(jokeBot);
        return botsApi;
    }
}
