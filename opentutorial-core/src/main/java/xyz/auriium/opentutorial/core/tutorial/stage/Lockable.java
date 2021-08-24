package xyz.auriium.opentutorial.core.tutorial.stage;

import java.util.UUID;

public interface Lockable {

    void setLockedMovement(UUID uuid, Boolean locked);
    void setLockedView(UUID uuid, Boolean locked);

    void removeOne(UUID uuid);
    void removeAll();

}
