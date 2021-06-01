package xyz.auriium.opentutorial.stage.action;

import xyz.auriium.opentutorial.stage.Stage;

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
