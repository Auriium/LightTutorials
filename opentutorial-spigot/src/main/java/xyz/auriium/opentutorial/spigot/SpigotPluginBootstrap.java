package xyz.auriium.opentutorial.spigot;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.opentutorial.core.InitialCentralizer;
import xyz.auriium.opentutorial.core.UserRegistry;
import xyz.auriium.opentutorial.core.config.CommonConfigCentralizer;
import xyz.auriium.opentutorial.core.config.ConfigCentralizer;
import xyz.auriium.opentutorial.core.config.ConfigExceptionHandler;
import xyz.auriium.opentutorial.core.event.inner.CommonEventBus;
import xyz.auriium.opentutorial.core.event.inner.InnerEventBus;
import xyz.auriium.opentutorial.core.tutorial.CommonConsumerRegistry;
import xyz.auriium.opentutorial.core.tutorial.ConsumerRegistry;

public class SpigotPluginBootstrap extends JavaPlugin {

    private final InnerEventBus eventBus = new CommonEventBus();
    private final UserRegistry<Player> userRegistry = new SpigotUserRegistry(this);
    private final ConsumerRegistry consumerRegistry = new CommonConsumerRegistry(eventBus);

    private final ConfigExceptionHandler exceptionHandler = new SpigotExceptionHandler(userRegistry);
    private final ConfigCentralizer configCentralizer = new CommonConfigCentralizer(exceptionHandler,consumerRegistry,getDataFolder().toPath());

    private final InitialCentralizer centralizer = new InitialCentralizer(configCentralizer,consumerRegistry);

    @Override
    public void onEnable() {
        centralizer.startup();
    }

    @Override
    public void onDisable() {
        centralizer.shutdown();
    }
}
