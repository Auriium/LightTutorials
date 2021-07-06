package xyz.auriium.opentutorial.core.stage.command;

import xyz.auriium.opentutorial.api.construct.Stage;

import java.util.Optional;

/**
 * Stage that runs commands as a player or as the console
 */
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
