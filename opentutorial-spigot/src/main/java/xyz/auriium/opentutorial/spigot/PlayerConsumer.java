package xyz.auriium.opentutorial.spigot;

import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class PlayerConsumer implements Consumer<String> {

    private final Player player;

    public PlayerConsumer(Player player) {
        this.player = player;
    }

    @Override
    public void accept(String s) {
        player.sendMessage(s);
    }
}
