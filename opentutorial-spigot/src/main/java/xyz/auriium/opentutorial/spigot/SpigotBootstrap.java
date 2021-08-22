package xyz.auriium.opentutorial.spigot;

import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.MessageType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.opentutorial.core.platform.Platform;
import xyz.auriium.opentutorial.core.platform.impl.PlatformDependentLoader;
import xyz.auriium.opentutorial.api.construct.Template;
import xyz.auriium.opentutorial.spigot.gui.ListMenu;
import xyz.auriium.opentutorial.spigot.gui.PreCreationMenu;
import xyz.auriium.opentutorial.spigot.platform.SpigotPlatformLauncher;

public class SpigotBootstrap extends JavaPlugin {

    private final SpigotPlatformLauncher launcher = new SpigotPlatformLauncher(this);

    private volatile PlatformDependentLoader<Player> loader;

    public void onLoad() {
        //TODO move stuff here
    }

    @Override
    public void onEnable() {
        //Initialize platform and loader
        //This is a necessity to allow reloads with how bukkit plugin loading is terribly designed.
        Platform<Player> platform = launcher.launch();
        loader = PlatformDependentLoader.build(platform);

        //Load current loader instance!
        loader.load();

        getServer().getPluginManager().registerEvents(new SpigotBusHook(platform,loader),this);

        //Initialize hacky acf bullshit (to be replaced with Branch!)

        ListMenu listMenu = new ListMenu(loader);
        PreCreationMenu preCreationMenu = new PreCreationMenu();

        BukkitCommandManager manager = new BukkitCommandManager(this);
        TutorialCommand command = new TutorialCommand(platform.userRegistry(), loader, listMenu, preCreationMenu);

        manager.getCommandContexts().registerContext(Template.class, new ACFTemplateContext(platform.userRegistry(), loader));
        manager.getCommandCompletions().registerCompletion("templates",s -> loader.getModule().templateController().getTemplateNames());

        manager.setFormat(MessageType.HELP, ChatColor.GRAY, ChatColor.BLUE, ChatColor.GRAY);
        manager.setFormat(MessageType.ERROR, ChatColor.RED);
        manager.setFormat(MessageType.SYNTAX, ChatColor.GRAY, ChatColor.BLUE);
        manager.setFormat(MessageType.INFO, ChatColor.GRAY, ChatColor.BLUE);
        manager.enableUnstableAPI("help");
        manager.registerCommand(command);


    }

    public void onDisable() {
        loader.close();
    }
}
