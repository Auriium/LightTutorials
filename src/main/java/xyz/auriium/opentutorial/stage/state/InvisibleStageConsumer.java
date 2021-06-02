package xyz.auriium.opentutorial.stage.state;

import xyz.auriium.opentutorial.centralized.Tutorial;
import xyz.auriium.opentutorial.centralized.server.UUIDRegistry;
import xyz.auriium.opentutorial.stage.StageConsumer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class InvisibleStageConsumer implements StageConsumer<InvisibleStage> {

    private final UUIDRegistry registry;
    private final Set<UUID> invisible = new HashSet<>();

    public InvisibleStageConsumer(UUIDRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void started(InvisibleStage options, Tutorial continuable) {
        UUID id = continuable.getIdentifier();

        registry.getPlayer(id).ifPresent(player -> {

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
            registry.getPlayer(uuid).ifPresent(player -> player.setInvisible(false));
        }
    }

    @Override
    public void close() {
        for (UUID uuid : invisible) {
            registry.getPlayer(uuid).ifPresent(player -> player.setInvisible(false));
        }
    }
}
