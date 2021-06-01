package xyz.auriium.opentutorial.data;

import me.aurium.beetle.api.datacore.DataCore;
import xyz.auriium.opentutorial.centralized.template.Template;

import java.sql.PreparedStatement;
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
            PreparedStatement statement = transact.getConnection().prepareStatement("INSERT INTO tries VALUES (?,?)");

            statement.setString(0,player.toString());

            return null;
        });
    }
}
