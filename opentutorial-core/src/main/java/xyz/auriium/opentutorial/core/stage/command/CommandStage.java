package xyz.auriium.opentutorial.core.stage.command;

import xyz.auriium.opentutorial.api.construct.Stage;

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

    public String getRunAsConsole() {
        return runAsConsole;
    }

    public String getRunAsPlayer() {
        return runAsPlayer;
    }
}
