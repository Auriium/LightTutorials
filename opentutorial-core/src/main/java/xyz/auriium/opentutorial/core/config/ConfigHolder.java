package xyz.auriium.opentutorial.core.config;

public interface ConfigHolder<C> {
    /**
     * NULLABLE,. I HATE THIS ENTIRE FUCKING CLASS BECAUSE I DIDNT DESIGN AROUND THE PROJECT BEING RELOADABLE
     * AND I FUCKING REGRET IT
     * @return nullable value of C
     */
    C get();

}
