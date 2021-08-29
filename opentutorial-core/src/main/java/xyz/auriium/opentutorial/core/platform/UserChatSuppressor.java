package xyz.auriium.opentutorial.core.platform;

import java.util.UUID;

public interface UserChatSuppressor {

    void setSuppressed(UUID uuid, Boolean locked);

    void removeOne(UUID uuid);
    void removeAll();

}
