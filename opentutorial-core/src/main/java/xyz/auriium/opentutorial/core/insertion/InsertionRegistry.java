package xyz.auriium.opentutorial.core.insertion;

import xyz.auriium.opentutorial.core.tutorial.stage.ProcessingInsertion;

import java.util.Collection;
import java.util.Optional;

public interface InsertionRegistry {

    Collection<ProcessingInsertion> getAllInsertions();
    Optional<ProcessingInsertion> getInsertion(String identifier);
    InsertionRegistry register(ProcessingInsertion insertion);

}
