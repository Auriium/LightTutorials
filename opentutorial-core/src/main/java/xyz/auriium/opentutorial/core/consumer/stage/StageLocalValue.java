package xyz.auriium.opentutorial.core.consumer.stage;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class StageLocalValue<T> {

    private volatile T value;

    private final boolean isLocal;
    private final Consumer<T> closeHook;

    public StageLocalValue(T value, boolean isLocal, Consumer<T> closeHook) {
        this.value = value;
        this.isLocal = isLocal;
        this.closeHook = closeHook;
    }

    public void setValue(T value) {
        Objects.requireNonNull(value);

        this.value = value;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void callCloseHook() {
        closeHook.accept(value);
    }

    public T get() {
        return value;
    }

}
