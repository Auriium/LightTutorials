package xyz.auriium.opentutorial.spigot.hook.command;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import xyz.auriium.openmineplatform.api.Platform;
import xyz.auriium.openmineplatform.api.interfaceable.Colorer;
import xyz.auriium.opentutorial.core.MissingServiceSupplier;
import xyz.auriium.opentutorial.core.PlatformDependentModule;

import java.util.ArrayList;
import java.util.List;

public class ListMenu implements GUIProducer{

    private final Platform platform;
    private final Colorer colorer;

    public ListMenu(Platform platform, Colorer colorer) {
        this.platform = platform;
        this.colorer = colorer;
    }

    @Override
    public ChestGui produce() {
        PlatformDependentModule module = platform.serviceRegistry().retrieve(PlatformDependentModule.class).orElseThrow(new MissingServiceSupplier("plugin-core"));
        /// FIXME: 8/26/2021 REMOVE THIS (replace with api and apiget)

        ChestGui gui = new ChestGui(6, colorer.color("&9Open&7Tutorial &7>> &eAll Tutorials")); //might need to add related plugin
        gui.setOnGlobalClick(click -> click.setCancelled(true));
        PaginatedPane pane = new PaginatedPane(0, 0, 9, 5);

        List<GuiItem> guiItems = new ArrayList<>();

        module.templateController().getTemplates().forEach((name,template) -> {
            GuiItem item = new GuiItem(new SpigotItemBuilder(Material.ENDER_EYE)
                    .setName(colorer.color("&7" + name.toUpperCase()))
                    .addLoreLine(colorer.color("&7"))
                    .addLoreLine(colorer.color("&9Left-Click &7to play this tutorial!"))
                    .toItemStack(), event -> {

                if (event.isLeftClick()) {
                    module.tutorialController().createNew(template,event.getWhoClicked().getUniqueId()).fireNext(); //play
                }

                event.getWhoClicked().closeInventory();
            });

            guiItems.add(item);
        });

        pane.populateWithGuiItems(guiItems);
        gui.addPane(pane);


//page selection
        StaticPane back = new StaticPane(0, 5, 1, 1);
        StaticPane forward = new StaticPane(8, 5, 1, 1);

        back.addItem(new GuiItem(new ItemStack(Material.ARROW), event -> {
            pane.setPage(pane.getPage() - 1);

            if (pane.getPage() == 0) {
                back.setVisible(false);
            }

            if (pane.getPages() <= 1) {
                back.setVisible(false);
            }

            forward.setVisible(true);
            gui.update();
        }), 0, 0);

        back.setVisible(false);

        forward.addItem(new GuiItem(new ItemStack(Material.ARROW), event -> {
            pane.setPage(pane.getPage() + 1);

            if (pane.getPage() == pane.getPages() - 1) {
                forward.setVisible(false);
            }

            back.setVisible(true);
            gui.update();
        }), 0, 0);

        gui.addPane(back);
        gui.addPane(forward);

        return gui;
    }
}
