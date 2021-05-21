package gg.qteam.lighttutorials.stage.await;

import gg.qteam.lighttutorials.model.SequentialTutorial;
import gg.qteam.lighttutorials.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class AbstractAwaitConsumer<T extends Stage,E extends IdentifiableEvent> implements AwaitConsumer<T, E> {

    private final Map<UUID, T> map = new HashMap<>();

    @Override
    public void started(T options, SequentialTutorial continuable) {
        map.put(continuable.getSpawnedIdentifier(),options);
    }

    @Override
    public void accept(E e) {
        T t = map.get(e.getSpawnedIdentifier());
        if (t != null) {
            consume(t,e);
        }
    }
}
