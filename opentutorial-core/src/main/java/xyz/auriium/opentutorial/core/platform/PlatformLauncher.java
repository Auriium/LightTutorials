package xyz.auriium.opentutorial.core.platform;

/**
 * Structure that initializes and then launches a corresponding platform
 */
public interface PlatformLauncher<T> {

    Platform<T> launch();

}
