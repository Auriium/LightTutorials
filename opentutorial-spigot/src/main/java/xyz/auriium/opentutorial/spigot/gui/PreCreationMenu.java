package xyz.auriium.opentutorial.spigot.gui;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import xyz.auriium.opentutorial.spigot.util.SpigotItemBuilder;

import static xyz.auriium.opentutorial.spigot.gui.ChatUtil.*;

public class PreCreationMenu implements GUIProducer{
    @Override
    public ChestGui produce() {
        ChestGui gui = new ChestGui(6, color("&9Open&7Tutorial &7&l>> &0Creation Confirmation"));
        StaticPane back = new StaticPane(4, 2, 1, 1);

        ItemStack stack = new SpigotItemBuilder(Material.RED_STAINED_GLASS)
                .setName(color("&c&lWARNING - READ THIS"))
                .addLoreLine(color("&7"))
                .addLoreLine(color("&7By clicking this you are creating"))
                .addLoreLine(color("&7a tutorial template that &conly exists"))
                .addLoreLine(color("&cin server memory.&7 This is done so that"))
                .addLoreLine(color("&7any corruption will not affect or erase"))
                .addLoreLine(color("&7any existing tutorials."))
                .addLoreLine(color("&7"))
                .addLoreLine(color("&7To save it you must press the compile button"))
                .addLoreLine(color("&7and move the generated file from the output folder"))
                .addLoreLine(color("&7to the tutorial folder."))
                .toItemStack();

        back.addItem(new GuiItem(stack, event -> {
            event.setCancelled(true);
        }),0,0);

        gui.addPane(back);

        return gui;
    }
}
