package me.aurium.opentutorial.data;

import me.aurium.beetle.api.datacore.DataCore;
import me.aurium.opentutorial.centralized.template.Template;

import java.util.UUID;

public class DataHandler {

    private final DataCore core;

    public DataHandler(DataCore core) {
        this.core = core;
    }

    public void generateTable() {

    }

    public void markTutorial(Template template, UUID player) {
        core.runConsumer(transact -> {
            transact.getConnection().prepareStatement("INSERT INTO tries (?)");

            return null;
        });
    }
}
