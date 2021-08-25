package xyz.auriium.opentutorial.core.config.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.error.InvalidConfigException;
import xyz.auriium.opentutorial.core.config.ConfigExceptionHandler;
import xyz.auriium.opentutorial.core.platform.TeachableRegistry;

import java.io.IOException;

public class WarningExceptionHandler implements ConfigExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger("OpenTutorial");

    private final TeachableRegistry teachableRegistry;

    public WarningExceptionHandler(TeachableRegistry teachableRegistry) {
        this.teachableRegistry = teachableRegistry;
    }

    @Override
    public void signal(IOException exception) {

        teachableRegistry.getAllAccessibleAudiences().forEach(audience ->  {
            audience.sendMessage("&7");
            audience.sendMessage("&9&lOPEN&7&lTUTORIAL &7>> &c&lCONFIGURATION ERROR!");
            audience.sendMessage("&7");
            audience.sendMessage("&7An error occurred trying to read your config!");
            audience.sendMessage("&7Error type >> &9&lInputOutput Exception &7(Details Below)");
            audience.sendMessage("&7");
            audience.sendMessage("&c" + exception.getMessage());
            audience.sendMessage("&7");
            audience.sendMessage("&7A detailed exception has been logged to your console. ");
            audience.sendMessage("&7This is a bug with the code and needs to be fixed.");
            audience.sendMessage("&7Please report this to the author on the plugin's GitHub.");
        });

    }

    @Override
    public void signal(InvalidConfigException exception) {
        if (exception instanceof BadValueException) {
            BadValueException exception1 = (BadValueException) exception;


            teachableRegistry.getAllAccessibleAudiences().forEach(audience ->  {
                audience.sendMessage("&7");
                audience.sendMessage("&9&lOPEN&7&lTUTORIAL &7>> &c&lCONFIGURATION ERROR!");
                audience.sendMessage("&7");
                audience.sendMessage("&7An error occurred trying to read your config!");
                audience.sendMessage("&7Error type >> &9&lBad Config Value &7(Details Below)");
                audience.sendMessage("&7");
                audience.sendMessage("&c" + exception1.getMessage());
                audience.sendMessage("&7");
                audience.sendMessage("&7A detailed exception has been logged to your console. ");
                audience.sendMessage("&7It is not a code error but merely a detail of user error.");
                audience.sendMessage("&7Fix configuration errors and reload the plugin.");
            });



        } else {

            teachableRegistry.getAllAccessibleAudiences().forEach(audience ->  {
                audience.sendMessage("&7");
                audience.sendMessage("&9&lOPEN&7&lTUTORIAL &7>> &c&lCONFIGURATION ERROR!");
                audience.sendMessage("&7");
                audience.sendMessage("&7An error occurred trying to read your config!");
                audience.sendMessage("&7Error type >> &9&lConfiguration Failure &7(Details Below)");
                audience.sendMessage("&7");
                audience.sendMessage("&c" + exception.getMessage());
                audience.sendMessage("&7");
                audience.sendMessage("&7A detailed exception has been logged to your console. ");
                audience.sendMessage("&7It is not a code error but merely a detail of user error.");
                audience.sendMessage("&7Fix configuration errors and reload the plugin.");
            });

        }

        logger.error("OpenTutorial",exception);
    }
}
