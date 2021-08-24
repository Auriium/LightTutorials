package xyz.auriium.opentutorial.core.tutorial.stage;

import java.util.UUID;

public interface Suppressor {

    void setSuppressed(UUID uuid, Boolean locked);

    void removeOne(UUID uuid);
    void removeAll();

}
