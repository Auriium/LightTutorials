package xyz.auriium.opentutorial.spigot.hook;

import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.openmineplatform.api.ServiceHook;
import xyz.auriium.opentutorial.core.CommonConsumerRegistry;
import xyz.auriium.opentutorial.core.ConsumerRegistry;
import xyz.auriium.opentutorial.spigot.stage.player.InvisibleStageHandler;
import xyz.auriium.opentutorial.spigot.stage.player.SoundStageHandler;

public class ConsumerRegistryHook implements ServiceHook<ConsumerRegistry> {

    @Override
    public Class<ConsumerRegistry> providedServiceType() {
        return ConsumerRegistry.class;
    }

    @Override
    public ConsumerRegistry provide(Platform platform) {
        return CommonConsumerRegistry.defaults()
                .register(new InvisibleStageHandler())
                .register(new SoundStageHandler());
    }

    @Override
    public String name() {
        return "consumer-registry-hook";
    }
}
