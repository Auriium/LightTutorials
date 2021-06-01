package xyz.auriium.opentutorial.centralized.config.tutorials;

import space.arim.dazzleconf.error.BadValueException;

public interface InterpretFunction<T,V> {

    V interpret(T t) throws BadValueException;

}
