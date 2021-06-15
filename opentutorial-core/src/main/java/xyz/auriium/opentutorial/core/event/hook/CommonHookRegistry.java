package xyz.auriium.opentutorial.core.event.hook;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CommonHookRegistry implements HookRegistry{

    private final Set<HookInsertion> insertionSet = new HashSet<>();

    @Override
    public HookRegistry addHook(HookInsertion hookInsertion) {
        insertionSet.add(hookInsertion);

        return this;
    }

    @Override
    public Collection<HookInsertion> getInsertions() {
        return insertionSet;
    }

    public static HookRegistry defaults() {
        return new CommonHookRegistry();
    }
}
