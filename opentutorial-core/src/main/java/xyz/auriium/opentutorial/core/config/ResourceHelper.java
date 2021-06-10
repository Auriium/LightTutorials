package xyz.auriium.opentutorial.core.config;

import space.arim.dazzleconf.ConfigurationFactory;
import space.arim.dazzleconf.ConfigurationOptions;
import space.arim.dazzleconf.error.InvalidConfigException;
import space.arim.dazzleconf.ext.snakeyaml.CommentMode;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlConfigurationFactory;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlOptions;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * all of this needs to be recoded when i'm more motivated to work on this
 * @param <T>
 */
public class ResourceHelper<T> implements ConfigHolder<T> {

    private final Path path;
    private final String fileName;
    private final ConfigExceptionHandler handler;
    private final ConfigurationFactory<T> factory;

    private T nullable;

    public ResourceHelper(Class<T> clazz, Path path, String fileName, ConfigExceptionHandler handler, ConfigurationOptions options) {
        this.path = path;
        this.fileName = fileName;
        this.handler = handler;
        this.factory = SnakeYamlConfigurationFactory.create(clazz,options,new SnakeYamlOptions.Builder()
                .commentMode(CommentMode.alternativeWriter()) // Enables writing YAML comments
                .build());

    }

    public T get() {
        return nullable;
    }

    public void reload() {
        try {
            reloadInternally();
        } catch (IOException e) {
            handler.handle(e);
        } catch (InvalidConfigException e) {
            handler.handle(e);
        }
    }

    //so fucking lazy

    private void reloadInternally() throws IOException, InvalidConfigException {

        Files.createDirectories(path);

        Path path = this.path.resolve(fileName);

        if (!Files.exists(path)) {

            try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)) {
                fileChannel.write(ByteBuffer.wrap(getResource(fileName).readAllBytes()));
            }

        }

        this.nullable = factory.load(path.toUri().toURL().openStream());
    }

    //copied
    private InputStream getResource(String filename) throws IOException {
        URL url = this.getClass().getClassLoader().getResource(filename);

        if (url == null) throw new IllegalStateException("no resource exists for path " + filename + " in the jar! something went wrong while compiling, contact the developer!");

        URLConnection connection = url.openConnection();
        connection.setUseCaches(false);
        return connection.getInputStream();
    }

}
