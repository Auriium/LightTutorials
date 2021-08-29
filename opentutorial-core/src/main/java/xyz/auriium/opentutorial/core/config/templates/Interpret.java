package xyz.auriium.opentutorial.core.config.templates;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import xyz.auriium.openmineplatform.api.binding.location.PlatformLocation;
import xyz.auriium.openmineplatform.api.binding.location.UnboundPlatformLocation;
import xyz.auriium.opentutorial.core.consumer.stage.Identifiers;

import java.util.ArrayList;
import java.util.List;
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

        if (nullable == null) return null;

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

    public static List<String> convertList(FlexibleType shitter) throws BadValueException {

        List<String> strings = new ArrayList<>();

        for (FlexibleType type : shitter.getList()) {
            strings.add(type.getString());
        }

        return strings;
    }

    public static PlatformLocation convertLocation(FlexibleType type) throws BadValueException {
        Map<String, FlexibleType> map = type.getMap((key, v) -> Map.entry(key.getString(), v));

        long x = map.get(Identifiers.LOC_X).getLong();
        long y = map.get(Identifiers.LOC_Y).getLong();
        long z = map.get(Identifiers.LOC_Z).getLong();

        FlexibleType typeA = map.get(Identifiers.LOC_PITCH);
        FlexibleType typeB = map.get(Identifiers.LOC_YAW);

        FlexibleType typeC = map.get("world");

        if (typeA != null && typeB != null) {
            long pitch = typeA.getLong();
            long yaw = typeB.getLong();

            if (typeC != null) {
                return UnboundPlatformLocation.of(x, y, z, pitch, yaw, typeC.getString());
            }
            return UnboundPlatformLocation.of(x, y, z, pitch, yaw);
        }

        if (typeC != null) {
            return UnboundPlatformLocation.of(x, y, z, typeC.getString());
        }

        return UnboundPlatformLocation.of(x, y, z);
    }
}
