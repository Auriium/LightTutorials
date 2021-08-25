package xyz.auriium.opentutorial.spigot.platform;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.opentutorial.core.platform.PlatformlessLocation;
import xyz.auriium.opentutorial.core.platform.Teachable;

import java.util.UUID;

public class SpigotTeachable implements Teachable {

    public static final String BYPASS_INVIS_PERMISSION = "opentutorial.bypass.invisible";

    private final JavaPlugin plugin;
    private final Player player;

    SpigotTeachable(JavaPlugin plugin, Player player) {
        this.plugin = plugin;
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
        player.sendTitle(color(title), color(subtitle), a, b, c);
    }

    @Override
    public void runAs(String command) {

        plugin.getServer().getScheduler().runTask(plugin,() -> {
            player.getServer().dispatchCommand(player,command);
        });

    }

    @Override
    public void runConsole(String command) {

        plugin.getServer().getScheduler().runTask(plugin,() -> {
            player.getServer().dispatchCommand(player.getServer().getConsoleSender(),command);
        });

    }

    @Override
    public boolean teleport(PlatformlessLocation location) {

        Location bukkitLocation = player.getLocation();

        float pitch = location.getPitch(bukkitLocation.getPitch());
        float yaw = location.getYaw(bukkitLocation.getYaw());

        World world = location.getWorld().map(string -> plugin.getServer().getWorld(string)).orElse(bukkitLocation.getWorld());

        player.teleport(new Location(world, location.getX(), location.getY(), location.getZ(), yaw, pitch));

        return true;
    }

    @Override
    public PlatformlessLocation getLocation() {
        Location loc = player.getLocation();

        return new PlatformlessLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getPitch(), loc.getYaw(), loc.getWorld() != null ? loc.getWorld().getName() : null);
    }

    @Override
    public void setInvisible(boolean invisible) {
        for (Player etern : plugin.getServer().getOnlinePlayers()) {
            if (etern.equals(player)) continue;
            if (!etern.hasPermission(BYPASS_INVIS_PERMISSION)) {
                etern.hidePlayer(plugin,player);
            }
        }
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

    @Override
    public void sendClickable(String message, String command, String hover) {
        //bungeechat sucks
        //remind me to use adventure

        TextComponent component = new TextComponent(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', message)));
        BaseComponent[] hoverComponent = new ComponentBuilder(ChatColor.translateAlternateColorCodes('&',hover)).create();

        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + command));
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverComponent));

        player.spigot().sendMessage(component);
    }

    String color(String string) {
        return ChatColor.translateAlternateColorCodes('&',string);
    }
}
