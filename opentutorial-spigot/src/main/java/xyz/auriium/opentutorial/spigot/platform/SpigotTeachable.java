package xyz.auriium.opentutorial.spigot.platform;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import xyz.auriium.opentutorial.core.platform.base.Teachable;

import java.util.UUID;

public class SpigotTeachable implements Teachable {

    private final Player player;

    public SpigotTeachable(Player player) {
        this.player = player;
    }

    @Override
    public UUID getID() {
        return player.getUniqueId();
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public void sendMessage(String string) {
        player.sendMessage(color(string));
    }

    @Override
    public void sendActionbar(String string) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(color(string)));
    }

    @Override
    public void sendTitle(String title, String subtitle, int a, int b, int c) {
        player.sendTitle(title, subtitle, a, b, c);
    }

    @Override
    public void runAs(String command) {
        player.getServer().dispatchCommand(player,command);
    }

    @Override
    public void runConsole(String command) {
        player.getServer().dispatchCommand(player.getServer().getConsoleSender(),command);
    }

    @Override
    public void teleport(int x, int y, int z) {
        player.teleport(new Location(player.getWorld(), x, y, z));
    }

    @Override
    public boolean teleport(int x, int y, int z, String world) {

        World worldIn = player.getServer().getWorld(world);

        if (worldIn == null) return false;

        player.teleport(new Location(worldIn, x, y, z));

        return true;
    }

    @Override
    public void teleport(int x, int y, int z, int pitch, int yaw) {
        player.teleport(new Location(player.getWorld(), x, y, z, pitch, yaw));
    }

    @Override
    public boolean teleport(int x, int y, int z, int pitch, int yaw, String world) {
        World worldIn = player.getServer().getWorld(world);

        if (worldIn == null) return false;

        player.teleport(new Location(worldIn, x, y, z, pitch, yaw));

        return true;
    }

    @Override
    public void setInvisible(boolean invisible) {
        player.setInvisible(invisible);
    }

    @Override
    public void setState(String state, boolean bool) {
        //TODO
    }

    @Override
    public boolean hasState(String state) {
        //TODO
        return false;
    }

    @Override
    public void playSound(String sound, float volume, float pitch) {
        player.playSound(player.getLocation(),sound,volume,pitch);
    }

    String color(String string) {
        return ChatColor.translateAlternateColorCodes('&',string);
    }
}
