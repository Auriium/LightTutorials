package xyz.auriium.opentutorial.spigot.platform;

import org.bukkit.entity.Player;
import xyz.auriium.opentutorial.core.platform.base.Teachable;

public class SpigotTeachable implements Teachable {

    private final Player player;

    public SpigotTeachable(Player player) {
        this.player = player;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void sendMessage(String string) {

    }

    @Override
    public void sendActionbar(String string) {

    }

    @Override
    public void sendTitle(String title, String subtitle, int a, int b, int c) {

    }

    @Override
    public void runAs(String command) {

    }

    @Override
    public void runConsole(String command) {

    }

    @Override
    public void teleport(int x, int y, int z) {

    }

    @Override
    public boolean teleport(int x, int y, int z, String world) {
        return false;
    }

    @Override
    public void setInvisible(boolean invisible) {

    }
}
