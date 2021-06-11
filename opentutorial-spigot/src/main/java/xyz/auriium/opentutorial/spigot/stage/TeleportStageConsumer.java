package xyz.auriium.opentutorial.spigot.stage;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;
import xyz.auriium.opentutorial.core.model.UserRegistry;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.BasicStageConsumer;

public class TeleportStageConsumer implements BasicStageConsumer<TeleportStage> {

    private final UserRegistry<Player> userRegistry;
    private final Server server;

    public TeleportStageConsumer(UserRegistry<Player> userRegistry, Server server) {
        this.userRegistry = userRegistry;
        this.server = server;
    }

    @Override
    public void started(TeleportStage options, Tutorial continuable) {

        userRegistry.getByUUID(continuable.getIdentifier()).ifPresent(player -> {
            World world = options.getWorld().equals("none") ? player.getWorld() : server.getWorld(options.getWorld());

            if (world == null) throw new IllegalStateException("Invalid world specified for tutorial! World " + options.getWorld() + " does not exist!");

            Location location = new Location(world,options.getX(),options.getY(),options.getZ());

            player.teleport(location);
        });

        continuable.fireNext();
    }

    @Override
    public Class<TeleportStage> stageClass() {
        return TeleportStage.class;
    }
}
