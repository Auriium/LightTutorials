package xyz.auriium.opentutorial.spigot.hook;

import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.openmineplatform.api.ServiceHook;
import xyz.auriium.openmineplatform.spigot.JavaPluginTelescope;
import xyz.auriium.opentutorial.core.CommonConsumerRegistry;
import xyz.auriium.opentutorial.core.ConsumerRegistry;
import xyz.auriium.opentutorial.spigot.stage.player.HoloStageHandler;
import xyz.auriium.opentutorial.spigot.stage.player.InvisibleStageHandler;
import xyz.auriium.opentutorial.spigot.stage.player.SoundStageHandler;

public class ConsumerRegistryHook implements ServiceHook<ConsumerRegistry> {

    @Override
    public Class<ConsumerRegistry> providedServiceType() {
        return ConsumerRegistry.class;
    }

    @Override
    public ConsumerRegistry provide(Platform platform) {
        ConsumerRegistry reg = CommonConsumerRegistry.defaults()
                .register(new InvisibleStageHandler())
                .register(new SoundStageHandler());

        if (platform.telescope(JavaPluginTelescope.EXCEPTIONAL).getServer().getPluginManager().isPluginEnabled("HolographicDisplays")) {
            reg.register(new HoloStageHandler());
        } else {
            platform.logger().warn("HolographicDisplays was not found! HoloStage will not be registered.");
        }

        return reg;
    }

    @Override
    public String name() {
        return "consumer-registry-hook";
    }
}
