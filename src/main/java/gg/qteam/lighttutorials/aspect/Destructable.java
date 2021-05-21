package gg.qteam.lighttutorials.aspect;

import java.util.UUID;

public interface Destructable {

    void closeSingle(UUID uuid);
    void closeAll();

}
