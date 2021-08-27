package xyz.auriium.opentutorial.core.consumer;

import xyz.auriium.opentutorial.core.ConsumerRegistry;
import xyz.auriium.opentutorial.core.consumer.stage.Stage;
import xyz.auriium.opentutorial.core.tutorial.ConsumerFailureConduit;
import xyz.auriium.opentutorial.core.tutorial.Tutorial;

import java.util.*;

//centralizer backed by a registry
public class CommonConsumerCentralizer implements ConsumerCentralizer {

    private final ConsumerRegistry registry;
    private final ConsumerFailureConduit consumerFailureConduit;

    public CommonConsumerCentralizer(ConsumerRegistry registry, ConsumerFailureConduit consumerFailureConduit) {
        this.registry = registry;
        this.consumerFailureConduit = consumerFailureConduit;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Stage, E> Optional<StageConsumer<T, E>> getConsumer(T stage) {
       return registry.get(stage.getClass()).map(consumer -> (StageConsumer<T,E>) consumer);
    }

    @Override
    public <T extends Stage, E> void consumeStage(T stage, Tutorial tutorial) {

        consumerFailureConduit.handle(tutorial, () ->
                getConsumer(stage)
                .orElseThrow(() -> new StageException(String.format("A stage was called to the registry but no responsible handler was found for it! (For: %s)", stage.getClass().getName())))
                .stageStarted(stage, tutorial)
        );

    }

}
