package xyz.auriium.opentutorial.spigot.gui;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.auriium.opentutorial.core.platform.impl.PlatformDependentLoader;
import xyz.auriium.opentutorial.spigot.util.SpigotItemBuilder;

import java.util.ArrayList;
import java.util.List;

import static xyz.auriium.opentutorial.spigot.gui.ChatUtil.color;

public class ListMenu implements GUIProducer{

    private final PlatformDependentLoader<Player> loader;

    public ListMenu(PlatformDependentLoader<Player> loader) {
        this.loader = loader;
    }

    @Override
    public ChestGui produce() {
        ChestGui gui = new ChestGui(6, color("&9Open&7Tutorial &7&l>> &0All Tutorials")); //might need to add related plugin
        PaginatedPane pane = new PaginatedPane(0, 0, 9, 5);

        List<GuiItem> guiItems = new ArrayList<>();

        loader.getModule().templateController().getTemplates().forEach((name,template) -> {
            GuiItem item = new GuiItem(new SpigotItemBuilder(Material.ENDER_EYE)
                    .setName(color("&7" + name.toUpperCase()))
                    .addLoreLine(color("&7"))
                    .addLoreLine(color("&9Left-Click &7to play this tutorial!"))
                    .addLoreLine(color("&9Right-Click &7to open a new copy in the editor!"))
                    .toItemStack(), event -> {

                if (event.isLeftClick()) {
                    loader.getModule().tutorialController().createNew(template,event.getWhoClicked().getUniqueId()).fireNext(); //play
                } else if (event.isRightClick()) {
                    event.getWhoClicked().sendMessage(ChatColor.RED + "Sorry, this feature has not been implemented yet!");
                }

                event.setCancelled(true);

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
