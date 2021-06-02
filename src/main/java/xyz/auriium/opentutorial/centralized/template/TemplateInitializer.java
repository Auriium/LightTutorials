package xyz.auriium.opentutorial.centralized.template;

import xyz.auriium.opentutorial.centralized.config.ReloadableHelper;
import xyz.auriium.opentutorial.centralized.config.tutorials.ConfTutorials;

import java.util.HashMap;
import java.util.Map;

/**
 * bad design choices
 */
public class TemplateInitializer {

    private final Map<String,Template> templates = new HashMap<>();

    private final ReloadableHelper<ConfTutorials> tutorials;

    public TemplateInitializer(ReloadableHelper<ConfTutorials> tutorials) {
        this.tutorials = tutorials;
    }

    public void repopulate() {
        templates.clear();

        this.tutorials.reload();
        this.tutorials.getConfig().getTemplates().forEach((id, temp) -> templates.put(id,new Template(id, temp.getPermission(), temp.getStages())));
    }

    public Map<String, Template> getTemplates() {
        return templates;
    }


}
