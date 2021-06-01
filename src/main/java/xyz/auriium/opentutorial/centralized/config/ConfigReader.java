package xyz.auriium.opentutorial.centralized.config;

import xyz.auriium.opentutorial.centralized.template.Template;

import java.util.List;

public interface ConfigReader {

    List<Template> generateFromConfig();

}
