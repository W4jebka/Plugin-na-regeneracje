package W4jebka.xyz.Core.Helper;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ChatUtil {

    public static String fix(String s) {
        if(s==null){
            return "";
        }
        return ChatColor.translateAlternateColorCodes('&', s
                .replace(">>", "»")
                .replace("<<", "«")
                .replace("<>", "✖")
                .replace("<SERCE>", "❤")
                .replace("<X>", "✘")
                .replace("<Y>", "✔")
                .replace("{UNDERLINE}", "۔")
                .replace("{KROPKA}", "●")
                .replace("{sx}", "➜"));
    }

    public static String bcColor(String text) {
        if (text == null) {
            return "";
        }
        return text = text.replace("title", "").replace("chat", "").replace("actionbar", "").replace(">>", "»").replace("&", "§");
    }

    public static void giveItemsToAllPlayers(final ItemStack... items) {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            giveItems(p, items);
        }
    }

    public static boolean sendMessage(final Collection<? extends CommandSender> collection, final String message) {
        for (final CommandSender cs : collection) {
            sendMessage(cs, message);
        }
        return true;
    }

    public static String locToString(final Location loc) {
        return String.valueOf(String.valueOf(String.valueOf(loc.getX()))) + ":" + loc.getY() + ":" + loc.getZ() + ":" + loc.getYaw() + ":" + loc.getPitch();
    }

    public static Location locFromString(final String str) {
        final String[] str2loc = str.split(":");
        final Location loc = new Location(Bukkit.getWorlds().get(0), 0.0, 0.0, 0.0, 0.0f, 0.0f);
        loc.setX(Double.parseDouble(str2loc[0]));
        loc.setY(Double.parseDouble(str2loc[1]));
        loc.setZ(Double.parseDouble(str2loc[2]));
        loc.setYaw(Float.parseFloat(str2loc[3]));
        loc.setPitch(Float.parseFloat(str2loc[4]));
        return loc;
    }

    public static String locToString(final double x, final double y, final double z) {
        return String.valueOf(String.valueOf(String.valueOf(x))) + ":" + y + ":" + z + ":" + 0.0f + ":" + 0.0f;
    }

    public static boolean isInteger(final String string) {
        return Pattern.matches("-?[0-9]+", string.subSequence(0, string.length()));
    }

    public static boolean sendMessage(final Collection<? extends CommandSender> collection, final String message, final String permission) {
        for (final CommandSender cs : collection) {
            sendMessage(cs, message, permission);
        }
        return true;
    }

    public static double round(final double value, final int decimals) {
        final double p = Math.pow(10.0, decimals);
        return Math.round(value * p) / p;
    }

    public static boolean sendMessage(final CommandSender sender, final String message, final String permission) {
        if (sender instanceof ConsoleCommandSender) {
            sendMessage(sender, message);
        }
        return permission != null && permission != "" && sender.hasPermission(permission) && sendMessage(sender, message);
    }

    public static boolean sendMessage(final CommandSender sender, final String message) {
        if (sender instanceof Player) {
            if (message != null || message != "") {
                sender.sendMessage(fixColor(message));
            }
        }
        else {
            sender.sendMessage(ChatColor.stripColor(fixColor(message)));
        }
        return true;
    }

    public static ItemStack getItemStackFromString(final String itemstack) {
        final String[] splits = itemstack.split("@");
        final String type = splits[0];
        final String data = (splits.length == 2) ? splits[1] : null;
        if (data == null) {
            return new ItemStack(Material.getMaterial(type), 1);
        }
        return new ItemStack(Material.getMaterial(type), 1, (short)Integer.parseInt(data));
    }

    public static String fixColor(String text) {
        if (text == null) {
            return "";
        }
        return text = text.replace("&", "§").replace(">>", "»").replace("<<", "«");
    }

    public static void giveItems(final Player p, final ItemStack... items) {
        final Inventory i = (Inventory)p.getInventory();
        final HashMap<Integer, ItemStack> notStored = (HashMap<Integer, ItemStack>)i.addItem(items);
        for (final Map.Entry<Integer, ItemStack> e : notStored.entrySet()) {
            p.getWorld().dropItemNaturally(p.getLocation(), (ItemStack)e.getValue());
        }
    }


    public static void sendMessageToAllAdmins(final String msg, final String permission) {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission(permission)) {
                sendMessage(p, msg);
            }
        }
    }


    public static void giveItems(final Player sender, final ItemStack build) {
    }

    public static String fix(final String string, final int i, final int j, final int k) {
        return null;
    }


}
