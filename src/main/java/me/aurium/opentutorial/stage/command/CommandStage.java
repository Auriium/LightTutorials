package me.aurium.opentutorial.stage.command;

import me.aurium.opentutorial.stage.Stage;

import java.util.Optional;

public class CommandStage implements Stage {

    private final String runAsConsole;
    private final String runAsPlayer;

    public CommandStage(String runAsConsole, String runAsPlayer) {
        this.runAsConsole = runAsConsole;
        this.runAsPlayer = runAsPlayer;
    }

    public Optional<String> getRunAsConsole() {
        return Optional.ofNullable(runAsConsole);
    }

    public Optional<String> getRunAsPlayer() {
        return Optional.ofNullable(runAsPlayer);
    }
}
