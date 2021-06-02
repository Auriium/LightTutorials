package xyz.auriium.opentutorial.core.config.types.tutorials;

import space.arim.dazzleconf.error.BadValueException;

public interface InterpretFunction<T,V> {

    V interpret(T t) throws BadValueException;

}
