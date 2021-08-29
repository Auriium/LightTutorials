package xyz.auriium.opentutorial.core.platform;

import java.util.UUID;

public interface UserLocker {

    void setLockedMovement(UUID uuid, Boolean locked);
    void setLockedView(UUID uuid, Boolean locked);

    void removeOne(UUID uuid);
    void removeAll();

}
