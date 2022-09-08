package W4jebka.xyz.Core.Commands;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import W4jebka.xyz.Core.Helper.ReflectionHelper;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;

public class AbstractCommand
        implements CommandExecutor {
    private static CommandMap commandMap;
    private final String command;
    private final String description;
    private final List<String> alias;
    private final String usage;
    private final String permMessage;
    private final String perm;

    public AbstractCommand(String command, String perm) {
        this(command, new ArrayList<String>(), perm);
    }

    public AbstractCommand(String command, String[] aliases, String perm) {
        this(command, Arrays.asList(aliases), perm);
    }

    private AbstractCommand(String command, List<String> aliases, String perm) {
        this.description = null;
        this.usage = null;
        this.permMessage = null;
        this.command = command.toLowerCase();
        this.alias = aliases;
        this.perm = perm;
        this.register();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        return false;
    }

    private void register() {
        ReflectCommand cmd = new ReflectCommand(this, this.command);
        if (this.alias != null) {
            cmd.setAliases(this.alias);
        }
        if (this.description != null) {
            cmd.setDescription(this.description);
        }
        if (this.usage != null) {
            cmd.setUsage(this.usage);
        }
        if (this.permMessage != null) {
            cmd.setPermissionMessage(this.permMessage);
        }
        this.getCommandMap().register("", (Command)cmd);
        cmd.setExecutor(this);
    }

    private CommandMap getCommandMap() {
        if (commandMap != null) {
            return commandMap;
        }
        try {
            commandMap = (CommandMap) ReflectionHelper.getValue(Bukkit.getServer(), "commandMap");
            return commandMap;
        }
        catch (Exception e) {
            e.printStackTrace();
            return this.getCommandMap();
        }
    }

    public String getPerm() {
        return this.perm;
    }
}
