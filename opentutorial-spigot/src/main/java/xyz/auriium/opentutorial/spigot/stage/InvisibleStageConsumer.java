package xyz.auriium.opentutorial.spigot.stage;

import org.bukkit.entity.Player;
import xyz.auriium.opentutorial.core.UserRegistry;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class InvisibleStageConsumer implements StageConsumer<InvisibleStage> {

    private final UserRegistry<Player> registry;
    private final Set<UUID> invisible = new HashSet<>();

    public InvisibleStageConsumer(UserRegistry<Player> registry) {
        this.registry = registry;
    }

    @Override
    public void started(InvisibleStage options, Tutorial continuable) {
        UUID id = continuable.getIdentifier();

        registry.getByUUID(id).ifPresent(player -> {

            if (options.isOn()) {
                player.setInvisible(true);
                invisible.add(id);
            } else {
                player.setInvisible(false);
                invisible.remove(id);
            }

        });

        continuable.fireNext();
    }

    @Override
    public Class<InvisibleStage> stageClass() {
        return InvisibleStage.class;
    }

    @Override
    public void closeSingle(UUID uuid) {
        if (invisible.remove(uuid)) {
            registry.getByUUID(uuid).ifPresent(player -> player.setInvisible(false));
        }
    }

    @Override
    public void close() {
        for (UUID uuid : invisible) {
            registry.getByUUID(uuid).ifPresent(player -> player.setInvisible(false));
        }
    }
}
