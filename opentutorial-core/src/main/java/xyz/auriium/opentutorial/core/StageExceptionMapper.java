package xyz.auriium.opentutorial.core;

import xyz.auriium.openmineplatform.api.interfaceable.Interfaceable;
import xyz.auriium.openmineplatform.api.interfaceable.user.User;
import xyz.auriium.openmineplatform.api.interfaceable.user.UserTelescope;
import xyz.auriium.openmineplatform.api.telescope.Telescope;
import xyz.auriium.openmineplatform.api.telescope.TelescopeMapping;
import xyz.auriium.opentutorial.core.consumer.StageException;

public class StageExceptionMapper<I,O> implements TelescopeMapping<O,I> {

    private final Telescope<I,O> telescope;

    public StageExceptionMapper(Telescope<I, O> telescope) {
        this.telescope = telescope;
    }

    @Override
    public O calculate(I i) {
        var result = telescope.telescope(i);
        if (!result.isSuccess()) throw new StageException(result.getError());

        return result.getCompletion();
    }

    public static final StageExceptionMapper<Interfaceable, User> USER = new StageExceptionMapper<>(new UserTelescope());
}
