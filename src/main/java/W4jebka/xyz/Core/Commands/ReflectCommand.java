package W4jebka.xyz.Core.Commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.io.IOError;

final class ReflectCommand
        extends Command {
    AbstractCommand exe;
    AbstractCommand cmd;

    ReflectCommand(AbstractCommand cmd, String command) {
        super(command);
        this.cmd = cmd;
        this.exe = null;
    }

    public void setExecutor(AbstractCommand exe) {
        this.exe = exe;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.exe.getPerm())) {
            return true;
        }
        try {
            if (this.exe != null) {
                this.exe.onCommand(sender, this, commandLabel, args);
            }
        }
        catch (IOError var5) {
            var5.printStackTrace();
        }
        return true;
    }
}
