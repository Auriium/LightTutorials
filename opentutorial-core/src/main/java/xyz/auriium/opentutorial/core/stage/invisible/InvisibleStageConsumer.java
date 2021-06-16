package xyz.auriium.opentutorial.core.stage.invisible;

import xyz.auriium.opentutorial.core.platform.base.TeachableRegistry;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.stage.StageConsumer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class InvisibleStageConsumer implements StageConsumer<InvisibleStage> {

    private final TeachableRegistry registry;
    private final Set<UUID> invisible = new HashSet<>();

    public InvisibleStageConsumer(TeachableRegistry registry) {
        this.registry = registry;
    }


    @Override
    public void started(InvisibleStage options, Tutorial continuable) {
        UUID id = continuable.getIdentifier();

        registry.getAudienceByUUID(id).ifPresent(player -> {

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
            registry.getAudienceByUUID(uuid).ifPresent(player -> player.setInvisible(false));
        }
    }

    @Override
    public void close() {
        for (UUID uuid : invisible) {
            registry.getAudienceByUUID(uuid).ifPresent(player -> player.setInvisible(false));
        }
    }
}
