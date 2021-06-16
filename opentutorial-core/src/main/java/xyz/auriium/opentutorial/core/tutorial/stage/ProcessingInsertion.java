package xyz.auriium.opentutorial.core.tutorial.stage;

import xyz.auriium.opentutorial.core.tutorial.ConsumerInsertion;
import xyz.auriium.opentutorial.core.tutorial.stage.StageInsertion;

/**
 * Logical combination of stage insertions and consumer insertion
 */
public interface ProcessingInsertion extends StageInsertion, ConsumerInsertion {
}
