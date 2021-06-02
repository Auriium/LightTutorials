package xyz.auriium.opentutorial.spigot.stage;

import xyz.auriium.opentutorial.core.tutorial.stage.Stage;

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
