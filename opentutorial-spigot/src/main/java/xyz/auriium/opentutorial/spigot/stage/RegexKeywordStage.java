package xyz.auriium.opentutorial.spigot.stage;

public class RegexKeywordStage {

    private final String regex;
    private final String runOnFail;

    private final boolean cancelOnFail;
    private final boolean commandOnFail;

    public RegexKeywordStage(String regex, String runOnFail, boolean cancelOnFail, boolean commandOnFail) {
        this.regex = regex;
        this.runOnFail = runOnFail;
        this.cancelOnFail = cancelOnFail;
        this.commandOnFail = commandOnFail;
    }
}
