package me.aurium.opentutorial.aspect;

import java.io.Closeable;
import java.io.IOException;
import java.util.UUID;

/**
 * Utility aspect for garbage collection. To be moved to Beetle.
 */
public interface KeyCloseable<T> extends Closeable {

    /**
     * Closes a single resource by key
     * @param uuid key
     */
    void closeSingle(T uuid);

    /**
     * Closes all resources
     */
    void closeAll();

    @Override
    default void close() throws IOException {
        closeAll();
    }
}
