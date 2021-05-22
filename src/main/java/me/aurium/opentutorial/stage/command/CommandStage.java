package me.aurium.opentutorial.stage.command;

import me.aurium.opentutorial.stage.Stage;

public class CommandStage implements Stage {

    private final String command;

    public CommandStage(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
