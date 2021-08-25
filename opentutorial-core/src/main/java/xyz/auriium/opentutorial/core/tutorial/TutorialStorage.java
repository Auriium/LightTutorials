package xyz.auriium.opentutorial.core.tutorial;

import java.util.Optional;

public interface TutorialStorage {

    <T> void register(String key, StageLocalValue<T> bound);
    <T> Optional<StageLocalValue<T>> retrieveOptional(String key);
    <T> StageLocalValue<T> retrieveOrThrow(String key);

    void registerHandler(String key);

    void closeStage();
    void closeTutorial();

}
