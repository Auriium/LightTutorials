package xyz.auriium.opentutorial.core.types.keyword;

/**
 * Stage that waits for the player to say a specific string matching a regex pattern.
 */
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
