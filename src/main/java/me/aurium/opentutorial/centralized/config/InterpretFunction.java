package me.aurium.opentutorial.centralized.config;

import space.arim.dazzleconf.error.BadValueException;

public interface InterpretFunction<T,V> {

    V interpret(T t) throws BadValueException;

}
