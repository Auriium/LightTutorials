package xyz.auriium.opentutorial.core.tutorial;

import java.util.Collection;
import java.util.Set;

public interface ConsumerRegistry {

    /**
     * Gets all insertions to add
     * @return immutable collection of insertions
     */
    Collection<ConsumerInsertion> getInsertions();

    /**
     * Adds an insertion to the plugin
     * @param insertion the insertion
     * @return the registry
     */
    ConsumerRegistry addInsertion(ConsumerInsertion insertion);

}
