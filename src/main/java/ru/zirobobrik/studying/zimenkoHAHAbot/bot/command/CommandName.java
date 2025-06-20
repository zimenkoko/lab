package ru.zirobobrik.studying.zimenkoHAHAbot.bot.command;

public enum CommandName {

    JOKE("/joke"),
    JOKES("/jokes"),
    HELP("/help"),
    DELETE("/delete"),
    CREATE("/create"),
    RANDOM("/random"),
    TOP("/top"),
    NO("/no");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}