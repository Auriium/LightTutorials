package xyz.auriium.opentutorial.centralized.config.tutorials;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;

import java.util.Map;
import java.util.function.Consumer;

/**
 * Utility class for shitcode serializers
 */
public class Interpret {

    public static String NO_STRING = "none";
    public static long NO_LONG = -1L; ///fixme this is bad but i dont know what to do about it
    public static boolean NO_BOOL = true;

    public static <T> T getRequired(String identifier, Map<String,FlexibleType> typeMap, InterpretFunction<FlexibleType,T> func) throws BadValueException {
        FlexibleType type = typeMap.get(identifier);

        if (type == null) {
            throw new BadValueException.Builder().key(identifier).message("No value present for value " + identifier + "!").build();
        } else {
            return func.interpret(type);
        }
    }

    public static <T> T getEllusive(String identifier, Map<String,FlexibleType> typeMap, InterpretFunction<FlexibleType,T> function, T defaultValue) throws BadValueException {
        FlexibleType nullable = typeMap.get(identifier);

        if (nullable == null) {
            return defaultValue;
        } else {
            return function.interpret(nullable);
        }
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

    public static void ifStringPresent(String string, Consumer<String> action) {
        if (!string.equals(NO_STRING)) {
            action.accept(string);
        }
    }

}
