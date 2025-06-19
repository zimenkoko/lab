package ru.zirobobrik.studying.zimenkoHAHAbot.bot.command;

import com.google.common.collect.ImmutableMap;
import ru.zirobobrik.studying.zimenkoHAHAbot.service.SendBotMessageService;
import ru.zirobobrik.studying.zimenkoHAHAbot.service.JokeServiceImpl;

import static ru.zirobobrik.studying.zimenkoHAHAbot.bot.command.CommandName.*;

public class CommandContainer {
    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService, JokeServiceImpl jokeService) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(JOKE.getCommandName(), new JokeCommand(sendBotMessageService, jokeService))
                .put(JOKES.getCommandName(), new JokesCommand(sendBotMessageService, jokeService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(DELETE.getCommandName(), new DeleteJokeCommand(sendBotMessageService, jokeService))
                .put(CREATE.getCommandName(), new CreateJokeCommand(sendBotMessageService, jokeService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}