package me.aurium.opentutorial.stage.response;

public class RegexStage {

    private final String regex;
    private final String runOnFail;

    private final boolean cancelOnFail;
    private final boolean commandOnFail;

    public RegexStage(String regex, String runOnFail, boolean cancelOnFail, boolean commandOnFail) {
        this.regex = regex;
        this.runOnFail = runOnFail;
        this.cancelOnFail = cancelOnFail;
        this.commandOnFail = commandOnFail;
    }
}
