package xyz.auriium.opentutorial.core.platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.error.InvalidConfigException;
import xyz.auriium.openmineplatform.api.interfaceable.InterfaceableRegistry;
import xyz.auriium.opentutorial.core.consumer.StageException;
import xyz.auriium.opentutorial.core.platform.MessagingExceptionHandler;

import java.io.IOException;

public class WarningExceptionHandler implements MessagingExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger("OpenTutorial");

    private final InterfaceableRegistry registry;

    public WarningExceptionHandler(InterfaceableRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void failIO(IOException exception) {

        registry.getAll().forEach(audience ->  {
            audience.sendString("&7|");
            audience.sendString("&7| &9&lOPEN&7&lTUTORIAL &7>> &c&lCONFIGURATION ERROR!");
            audience.sendString("&7|");
            audience.sendString("&7| An error occurred trying to read your config!");
            audience.sendString("&7| Error type >> &9&lInputOutput Exception");
            audience.sendString("&7| ");
            audience.sendString("&7| &c" + exception.getMessage());
            audience.sendString("&7| ");
            audience.sendString("&7| A detailed exception has been logged to your console. ");
            audience.sendString("&7| This is a bug with the code and needs to be fixed.");
            audience.sendString("&7| Please report this to the author on the plugin's GitHub.");
        });

    }

    @Override
    public void failConfig(InvalidConfigException exception) {
        if (exception instanceof BadValueException) {
            BadValueException exception1 = (BadValueException) exception;


            registry.getAll().forEach(audience ->  {

                audience.sendString("&7|");
                audience.sendString("&7| &9&lOPEN&7&lTUTORIAL &7>> &c&lCONFIGURATION ERROR!");
                audience.sendString("&7|");
                audience.sendString("&7| An error occurred trying to read your config!");
                audience.sendString("&7| Error type >> &9&lConfiguration Failure");
                audience.sendString("&7| ");
                audience.sendString("&7| &c" + exception.getMessage());
                audience.sendString("&7| ");
                audience.sendString("&7| A detailed exception has been logged to your console. ");
                audience.sendString("&7| It is not a code error but merely a detail of user error.");
                audience.sendString("&7| Fix configuration errors and reload the plugin.");
            });



        } else {

            registry.getAll().forEach(audience ->  {
                audience.sendString("&7|");
                audience.sendString("&7| &9&lOPEN&7&lTUTORIAL &7>> &c&lCONFIGURATION ERROR!");
                audience.sendString("&7|");
                audience.sendString("&7| An error occurred trying to read your config!");
                audience.sendString("&7| Error type >> &9&lConfiguration Failure");
                audience.sendString("&7| ");
                audience.sendString("&7| &c" + exception.getMessage());
                audience.sendString("&7| ");
                audience.sendString("&7| A detailed exception has been logged to your console. ");
                audience.sendString("&7| This is a bug with the code and needs to be fixed.");
                audience.sendString("&7| Please report this to the author on the plugin's GitHub.");
            });

        }

        logger.error("OpenTutorial",exception);
    }

    @Override
    public void failStage(StageException exception) {

    }
}
