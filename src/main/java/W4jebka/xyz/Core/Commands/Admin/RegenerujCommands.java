package W4jebka.xyz.Core.Commands.Admin;

import W4jebka.xyz.Core.Commands.AbstractCommand;
import W4jebka.xyz.Core.Helper.ChatUtil;
import W4jebka.xyz.Core.Helper.Terrain;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.*;

public class RegenerujCommands extends AbstractCommand {
    public RegenerujCommands() {
        super("regeneruj", "W4jebka.regeneruj");
    }
    private List<Terrain> toRegenerate = new ArrayList<Terrain>();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player))
            return true;
        Player player = (Player) sender;
        Inventory inventory = Bukkit.createInventory(null, 5*9, "Regeneracja");

        int i = 0;
        for (Iterator<Terrain> it = toRegenerate.iterator(); it.hasNext();) {
            Terrain terrain = it.next();
            Location location = (Location) terrain.getLocation();

            ItemStack itemStack = new ItemStack(Material.GRASS, 1);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setLore(Arrays.asList(
                    ChatUtil.fix("&cData utworzenia: &7" + new SimpleDateFormat("YYYY-mm-dd HH:mm:ss").format(new Date(terrain.getTime()))),
                    ChatUtil.fix("&cLokalizacja: &7x=" + location.getBlockX() + " y=" + location.getBlockY() + " z=" + location.getBlockZ())
            ));
            itemStack.setItemMeta(itemMeta);

            inventory.setItem(i, itemStack);
            i++;
        }
        player.openInventory(inventory);

        return true;
    }
}
