package xyz.auriium.opentutorial.core.config.templates.util;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.opentutorial.core.config.templates.InterpretFunction;

import java.util.Map;
import java.util.function.Consumer;

public class Interpret {



    public static <T> T getRequired(String identifier, Map<String,FlexibleType> typeMap, InterpretFunction<FlexibleType,T> func) throws BadValueException {
        FlexibleType type = typeMap.get(identifier);

        if (type == null) {
            throw new BadValueException.Builder().key(identifier).message("No value present for key '" + identifier + "'!").build();
        } else {
            return func.interpret(type);
        }
    }

    public static <T> T getAlternative(String identifier, Map<String,FlexibleType> typeMap, InterpretFunction<FlexibleType,T> function, T defaultValue) throws BadValueException {
        FlexibleType nullable = typeMap.get(identifier);

        if (nullable == null) {
            return defaultValue;
        } else {
            return function.interpret(nullable);
        }
    }

    public static <T> T getNullable(String identifier, Map<String,FlexibleType> typeMap, InterpretFunction<FlexibleType,T> function) throws BadValueException {
        FlexibleType nullable = typeMap.get(identifier);

        return function.interpret(nullable);
    }

    /**
     * TODO: replace this with a function returning a class like optionals but instead with a DefaultValue
     * @param current
     * @param defaultVal
     * @param action
     * @param <T>
     */
    public static <T> void ifPresent(T current, T defaultVal, Consumer<T> action) {
        if (!current.equals(defaultVal)) {
            action.accept(current);
        }
    }

}
