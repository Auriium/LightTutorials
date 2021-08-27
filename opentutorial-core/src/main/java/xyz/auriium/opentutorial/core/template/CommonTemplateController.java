package xyz.auriium.opentutorial.core.template;

import xyz.auriium.opentutorial.core.config.templates.TutorialsConfig;
import xyz.auriium.opentutorial.core.tutorial.CommonTemplate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommonTemplateController implements TemplateController {

    private final Map<String, Template> templates;

    public CommonTemplateController(Map<String, Template> templates) {
        this.templates = templates;
    }

    @Override
    public Optional<Template> getByIdentifier(String identifier) {
        return Optional.ofNullable(templates.get(identifier));
    }

    @Override
    public Collection<String> getTemplateNames() {
        return templates.keySet();
    }

    @Override
    public Map<String,Template> getTemplates() {
        return templates;
    }

    public static TemplateController build(TutorialsConfig config) {

        Map<String,Template> map = new HashMap<>();

        config.getTemplates().forEach((string,section) -> {
            map.put(string,new CommonTemplate(section.getPermission(), section.getStages()));
        });

        return new CommonTemplateController(map);
    }
}
