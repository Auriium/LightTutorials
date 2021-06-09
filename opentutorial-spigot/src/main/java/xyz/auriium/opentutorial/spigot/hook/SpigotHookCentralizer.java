package xyz.auriium.opentutorial.spigot.hook;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.opentutorial.core.event.outer.HookCentralizer;

public class SpigotHookCentralizer implements HookCentralizer {

    private final JavaPlugin plugin;

    private final LockListener lockListener;
    private final StartupListener startupListener;
    private final EventBusListener eventBusListener;

    public SpigotHookCentralizer(JavaPlugin plugin, LockListener lockListener, StartupListener startupListener, EventBusListener eventBusListener) {
        this.plugin = plugin;
        this.lockListener = lockListener;
        this.startupListener = startupListener;
        this.eventBusListener = eventBusListener;
    }

    @Override
    public void startup() {
        PluginManager manager = plugin.getServer().getPluginManager();

        manager.registerEvents(lockListener,plugin);
        manager.registerEvents(startupListener,plugin);
        manager.registerEvents(eventBusListener,plugin);
    }

    @Override
    public void reload() {
        //nothing
        lockListener.getLockMovement().clear();
        lockListener.getLockView().clear();

    }

    @Override
    public void shutdown() {

    }
}
