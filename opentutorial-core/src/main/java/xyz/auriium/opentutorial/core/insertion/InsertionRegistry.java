package xyz.auriium.opentutorial.core.insertion;

import xyz.auriium.opentutorial.core.tutorial.stage.StageInsertion;

import java.util.Collection;
import java.util.Optional;

public interface InsertionRegistry {

    Collection<StageInsertion> getAllInsertions();
    Optional<StageInsertion> getInsertion(String identifier);
    InsertionRegistry register(StageInsertion insertion);

}
