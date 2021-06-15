package xyz.auriium.opentutorial.core.event.hook;

import xyz.auriium.opentutorial.core.event.InnerEventConsumer;

import java.util.Collection;

public interface HookRegistry {

    HookRegistry addHook(HookInsertion hookInsertion);

    Collection<HookInsertion> getInsertions();

}
