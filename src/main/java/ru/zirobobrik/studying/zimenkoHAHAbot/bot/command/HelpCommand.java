package ru.zirobobrik.studying.zimenkoHAHAbot.bot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.zirobobrik.studying.zimenkoHAHAbot.service.SendBotMessageService;

import static ru.zirobobrik.studying.zimenkoHAHAbot.bot.command.CommandName.*;

public class HelpCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public static final String HELP_MESSAGE = String.format("\uD83D\uDC47\uD83C\uDFFF<b>Здарова. Доступные команды</b>\uD83D\uDC47\uD83C\uDFFF\n\n"

                    + "%s - получить все шутки\n"
                    + "%s \"id\" - получить шутку по id\n"
                    + "%s \"id\" - удалить шутку по id\n"
                    + "%s \"текст анекдота\" - добавить новую шутку\n\n"
                    + "%s - просмотреть список команд",
            JOKES.getCommandName(), JOKE.getCommandName(), DELETE.getCommandName(), CREATE.getCommandName(), HELP.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}