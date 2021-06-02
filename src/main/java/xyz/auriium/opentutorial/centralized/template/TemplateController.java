package xyz.auriium.opentutorial.centralized.template;

import space.arim.dazzleconf.ConfigurationOptions;
import xyz.auriium.opentutorial.centralized.config.ConfigExceptionHandler;
import xyz.auriium.opentutorial.centralized.config.ReloadableHelper;
import xyz.auriium.opentutorial.centralized.config.tutorials.ConfTutorials;
import xyz.auriium.opentutorial.centralized.config.tutorials.StageConfSerializer;
import xyz.auriium.opentutorial.centralized.registry.ConsumerRegistry;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TemplateController {

    private final Map<String,Template> templates = new HashMap<>();
    private final ReloadableHelper<ConfTutorials> tutorials;

    public TemplateController(Path delegatePath, String delegateName, ConfigExceptionHandler handler, ConsumerRegistry registry) {
        this.tutorials = new ReloadableHelper<>(
                ConfTutorials.class,delegatePath,
                delegateName,
                handler,
                new ConfigurationOptions.Builder().addSerialiser(new StageConfSerializer(registry)).build()
        );
    }

    public void reload() {
        templates.clear();

        this.tutorials.reload();
        this.tutorials.getConfig().getTemplates().forEach((id, temp) -> templates.put(id,new Template(id, temp.getPermission(), temp.getStages())));
    }

    public Optional<Template> loadTemplate(String identifier) {
        return Optional.ofNullable(templates.get(identifier));
    }


}
