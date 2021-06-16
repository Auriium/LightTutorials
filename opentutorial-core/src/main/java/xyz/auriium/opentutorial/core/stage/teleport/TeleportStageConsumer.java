package xyz.auriium.opentutorial.core.stage.teleport;

import xyz.auriium.opentutorial.core.StageFailureException;
import xyz.auriium.opentutorial.core.config.templates.impl.Interpret;
import xyz.auriium.opentutorial.core.platform.base.TeachableRegistry;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.BasicStageConsumer;

public class TeleportStageConsumer implements BasicStageConsumer<TeleportStage> {

    private final TeachableRegistry userRegistry;

    public TeleportStageConsumer(TeachableRegistry userRegistry) {
        this.userRegistry = userRegistry;
    }

    @Override
    public void started(TeleportStage options, Tutorial continuable) {

        userRegistry.getAudienceByUUID(continuable.getIdentifier()).ifPresent(player -> {
            String worldName = options.getWorld();
            int x = options.getX();
            int y = options.getY();
            int z = options.getZ();

            //clean this up
            if (options.getPitch() == Interpret.NO_INT || options.getYaw() == Interpret.NO_INT) {
                if (worldName.equals("none")) {
                    player.teleport(x,y,z);
                } else {
                    boolean isSuccessful = player.teleport(x,y,z,worldName);

                    if (!isSuccessful) {
                        throw new StageFailureException("Invalid world specified for tutorial! World " + worldName + " does not exist or cannot be loaded by OpenTutorial platform handle!");
                    }
                }
            } else {
                int pitch = options.getPitch();
                int yaw = options.getYaw();

                if (worldName.equals("none")) {
                    player.teleport(x,y,z,pitch,yaw);
                } else {
                    boolean isSuccessful = player.teleport(x,y,z,pitch,yaw,worldName);

                    if (!isSuccessful) {
                        throw new StageFailureException("Invalid world specified for tutorial! World " + worldName + " does not exist or cannot be loaded by OpenTutorial platform handle!");
                    }
                }
            }


        });

        continuable.fireNext();
    }

    @Override
    public Class<TeleportStage> stageClass() {
        return TeleportStage.class;
    }
}
