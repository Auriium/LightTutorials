package xyz.auriium.opentutorial.core.data;

import xyz.auriium.beetle.datacore.DataCore;
import java.sql.PreparedStatement;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class TableDataHandler implements DataHandler{

    private final DataCore core;

    public TableDataHandler(DataCore core) {
        this.core = core;
    }

    @Override
    public CompletableFuture<Boolean> isPresent(String templateID, UUID userID) {
        return CompletableFuture.supplyAsync(() -> {
            //sql
            return false;
        });
    }

    @Override
    public void insertPresent(String templateID, UUID userID) {

    }

}
