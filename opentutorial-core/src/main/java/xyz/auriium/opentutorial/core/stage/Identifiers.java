package xyz.auriium.opentutorial.core.stage;

/**
 * Utility class defining the identifiers used to label different stage values. It is highly suggested that
 * if you are implementing your own stage you either subscribe to these values (if they correspond to your use case
 * e.g. if you have a custom click-entity-at-location stage, subscribe to locx,locy,locz) or make your own identifier class.
 * I was debating making this customizable but honestly at this point i don't have the effort to do that.
 */
public class Identifiers {

    public static final String CHAT_CHAT = "chat";
    public static final String CHAT_ACTIONBAR = "actionbar";
    public static final String CHAT_TITLE = "title";
    public static final String CHAT_SUBTITLE = "subtitle";

    public static final String SUPPRESS_CHAT = "suppress_chat";

    public static final String LOC_X = "x";
    public static final String LOC_Y = "y";
    public static final String LOC_Z = "z";
    public static final String LOC_PITCH = "pitch";
    public static final String LOC_YAW = "yaw";
    public static final String LOC_WORLD = "world";

    public static final String DELAY = "delay";

    public static final String DELAYTYPE_MAX_DELAY = "max_delay";
    public static final String DELAYTYPE_FORMAT = "actionbar_format";

    public static final String LIST_KEYWORDS = "keywords";

    public static final String CORRECT_OPTION = "correct_option";
    public static final String FAIL_CANCEL = "cancel_on_fail";
    public static final String FAIL_COMMAND = "command_on_fail";

    public static final String COMMAND_CONSOLE = "run_as_console";
    public static final String COMMAND_PLAYER = "run_as_player";

    public static final String INVISIBLE = "is_invisible";

    public static final String LOCK_MOVE = "lock_movement";
    public static final String LOCK_VIEW = "lock_view";

    public static final String SOUND_NAME = "sound_name";
    public static final String SOUND_VOLUME = "volume";
    public static final String SOUND_PITCH = "pitch";
}
