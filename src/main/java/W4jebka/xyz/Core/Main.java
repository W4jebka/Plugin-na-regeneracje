package W4jebka.xyz.Core;

import W4jebka.xyz.Core.Commands.Admin.RegenerujCommands;
import W4jebka.xyz.Core.Events.ExplodeListener;
import W4jebka.xyz.Core.Helper.Regenerate;
import W4jebka.xyz.Core.Helper.Terrain;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Main extends JavaPlugin implements Listener, CommandExecutor {
    public static Main instance;
    public static Main getInstance() {
        return instance;
    }
    private List<Terrain> toRegenerate = new ArrayList<Terrain>();

    @Override
    public void onEnable() {
        instance = this;
        this.registerCommands();
        this.registerEvents();
    }

    private void registerEvents() {
        new ExplodeListener(this);
    }
    private void registerCommands() {
        new RegenerujCommands();
    }



    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getInventory() == null || event.getCurrentItem() == null || event.getSlotType().equals(InventoryType.SlotType.OUTSIDE))
            return;

        if (!event.getView().getTitle().equalsIgnoreCase("Regeneracja"))
            return;

        Inventory inventory = event.getInventory();
        event.setCancelled(true);

        final Player player = (Player) event.getWhoClicked();

        int index = event.getSlot();
        if (index+1 > toRegenerate.size())
            return;

        inventory.removeItem(inventory.getItem(index));

        player.closeInventory();
        player.openInventory(inventory);

        Terrain terrain = toRegenerate.get(index);
        toRegenerate.remove(index);

        new Regenerate(player, terrain).runTaskTimer(this, 8L, 5L);
    }
}
