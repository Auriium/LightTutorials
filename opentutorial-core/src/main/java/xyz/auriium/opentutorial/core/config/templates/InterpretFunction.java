package xyz.auriium.opentutorial.core.config.templates;

import space.arim.dazzleconf.error.BadValueException;

public interface InterpretFunction<T,V> {

    V interpret(T t) throws BadValueException;

}
