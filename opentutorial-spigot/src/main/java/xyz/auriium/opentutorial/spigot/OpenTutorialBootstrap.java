package xyz.auriium.opentutorial.spigot;

import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.MessageType;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.auriium.openmineplatform.api.HookData;
import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.openmineplatform.api.plugin.PluginRepresentation;
import xyz.auriium.openmineplatform.api.plugin.ReloadablePluginState;
import xyz.auriium.openmineplatform.spigot.JavaPluginTelescope;
import xyz.auriium.openmineplatform.spigot.SpigotBootstrap;
import xyz.auriium.opentutorial.core.OpenTutorialRepresentation;
import xyz.auriium.opentutorial.core.PlatformDependentModule;
import xyz.auriium.opentutorial.core.template.Template;
import xyz.auriium.opentutorial.spigot.hook.*;

public class OpenTutorialBootstrap extends SpigotBootstrap {

    @Override
    public PluginRepresentation representation() {
        return new Representation();
    }

    public static class Representation extends OpenTutorialRepresentation {
        @Override
        public HookData getInsertionData() {
            return HookData.make()
                    .addServiceHook(new LockableHook())
                    .addServiceHook(new SuppressorHook())
                    .addServiceHook(new ExceptionHandlerHook())
                    .addServiceHook(new ConsumerRegistryHook())
                    .addPlatformHook(new MainHook());
        }

        @Override
        public void postStartup(Platform platform, PlatformDependentModule module, ReloadablePluginState state) {
            JavaPlugin plugin = platform.telescope(JavaPluginTelescope.EXCEPTIONAL);

            BukkitCommandManager manager = new BukkitCommandManager(plugin);
            TutorialCommand command = new TutorialCommand(state, platform);

            manager.getCommandContexts().registerContext(Template.class, new ACFTemplateContext(platform));
            manager.getCommandCompletions().registerCompletion("templates", new ACFConsumer(platform));

            manager.setFormat(MessageType.HELP, ChatColor.GRAY, ChatColor.BLUE, ChatColor.GRAY);
            manager.setFormat(MessageType.ERROR, ChatColor.RED);
            manager.setFormat(MessageType.SYNTAX, ChatColor.GRAY, ChatColor.BLUE);
            manager.setFormat(MessageType.INFO, ChatColor.GRAY, ChatColor.BLUE);
            manager.enableUnstableAPI("help");
            manager.registerCommand(command);
        }
    }

}
