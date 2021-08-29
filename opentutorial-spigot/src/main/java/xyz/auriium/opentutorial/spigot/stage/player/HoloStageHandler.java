package xyz.auriium.opentutorial.spigot.stage.player;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.openmineplatform.api.binding.location.PlatformLocation;
import xyz.auriium.openmineplatform.spigot.JavaPluginTelescope;
import xyz.auriium.opentutorial.core.config.templates.Interpret;
import xyz.auriium.opentutorial.core.consumer.BasicConsumer;
import xyz.auriium.opentutorial.core.consumer.StageException;
import xyz.auriium.opentutorial.core.consumer.stage.Identifiers;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.consumer.stage.StageLocalValue;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;
import xyz.auriium.opentutorial.core.tutorial.TutorialStorage;

import java.util.List;
import java.util.Map;

public class HoloStageHandler implements BasicConsumer<HoloStage> {
    @Override
    public String identifier() {
        return "holo";
    }

    @Override
    public Stage deserialize(Map<String, FlexibleType> map) throws BadValueException {

        List<String> strings = Interpret.getRequired(Identifiers.STRINGS, map, Interpret::convertList);
        Integer stay = Interpret.getRequired(Identifiers.STAY, map, FlexibleType::getInteger);
        PlatformLocation location = Interpret.getNullable(Identifiers.LOC, map, Interpret::convertLocation);
        boolean follow = Interpret.getRequired(Identifiers.FOLLOW, map, FlexibleType::getBoolean);

        return new HoloStage(strings, location, follow, stay);
    }

    @Override
    public void stageStarted(HoloStage options, Tutorial tutorial) throws StageException {

        JavaPlugin plugin = tutorial.getPlatform().telescope(JavaPluginTelescope.EXCEPTIONAL);
        Player player = plugin.getServer().getPlayer(tutorial.getIdentifier());

        if (player == null) throw new StageException("User is not present!");



        Location location = player.getLocation();

        if (options.getStartingLocation() != null) {
            location = derive(plugin, options.getStartingLocation(), player);
        }

        Hologram hologram = HologramsAPI.createHologram(plugin, location);

        for (String str : options.getStrings()) {
            hologram.appendTextLine(str);
        }

        TutorialStorage storage = tutorial.localStorage();

        storage.register("hologram", new StageLocalValue<>(hologram, false, Hologram::delete));

        HoloRunnable runnable = new HoloRunnable(1, hologram, player, options.isFollowPlayer());
        BukkitTask task = plugin.getServer().getScheduler().runTaskTimer(plugin, runnable, 1, 1);

        tutorial.localStorage().register("hologram_runnable", new StageLocalValue<>(task, false, BukkitTask::cancel));
    }

    @Override
    public Class<HoloStage> stageClass() {
        return HoloStage.class;
    }

    Location derive(Plugin plugin, PlatformLocation location, Player player) {
        long x = location.getX();
        long y = location.getY();
        long z = location.getZ();

        if (location.getPitch() == null || location.getYaw() == null) {
            if (location.getWorld() == null) {
                return new Location(player.getWorld(), x, y, z);
            }

            World world = plugin.getServer().getWorld(location.getWorld());

            return new Location(world == null ? player.getWorld() : world, x, y, z);
        }

        long pitch = location.getPitch();
        long yaw = location.getYaw();

        if (location.getWorld() == null) {
            return new Location(player.getWorld(), x, y, z, pitch, yaw);
        }

        World world = plugin.getServer().getWorld(location.getWorld());

        return new Location(world == null ? player.getWorld() : world, x, y, z, pitch, yaw);
    }
}
