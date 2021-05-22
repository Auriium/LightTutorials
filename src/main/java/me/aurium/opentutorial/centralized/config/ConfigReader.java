package me.aurium.opentutorial.centralized.config;

import me.aurium.opentutorial.centralized.template.Template;

import java.util.List;

public interface ConfigReader {

    List<Template> generateFromConfig();

}
