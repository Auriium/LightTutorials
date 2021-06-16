package xyz.auriium.opentutorial.spigot;

import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.InvalidCommandArgument;
import co.aikar.commands.MessageType;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.opentutorial.core.platform.impl.Platform;
import xyz.auriium.opentutorial.core.platform.impl.PlatformDependentLoader;
import xyz.auriium.opentutorial.core.tutorial.Template;
import xyz.auriium.opentutorial.spigot.platform.SpigotPlatformLauncher;

public class SpigotBootstrap extends JavaPlugin {

    private final SpigotPlatformLauncher launcher = new SpigotPlatformLauncher(this);

    //This is a necessity to allow reloads with how bukkit plugin loading is terribly designed.
    private volatile Platform platform;
    private volatile PlatformDependentLoader loader;

    @Override
    public void onEnable() {
        //Initialize platform and loader
        platform = launcher.launch();
        loader = PlatformDependentLoader.build(platform);

        //Load current loader instance!
        loader.load();

        //Initialize hacky acf bullshit (to be replaced with Branch!)
        BukkitCommandManager manager = new BukkitCommandManager(this);
        TutorialCommand command = new TutorialCommand(loader);

        manager.getCommandContexts().registerContext(Template.class, new ACFTemplateContext(loader));
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
