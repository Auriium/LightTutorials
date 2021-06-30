package xyz.auriium.opentutorial.core.stage.chat;

import java.util.UUID;

public interface Suppressor {

    void setSuppressed(UUID uuid, Boolean locked);

    void removeOne(UUID uuid);
    void removeAll();

}
