package dev.etcroot.oprotection.commands;

// Bukkit Libs
import dev.etcroot.oprotection.OProtection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// Java Libs
import java.util.List;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List allowedOps = OProtection.cfg.getStringList("allowed-op");
        String prefix = OProtection.cfg.getString("prefix");
        String noPerms = OProtection.cfg.getString("no-perms");
        String reloaded = OProtection.cfg.getString("plugin-reloaded");
        Player p = (Player) sender;
        if(sender instanceof Player) {
            if(sender.isOp()) {
                if(allowedOps.contains(p.getUniqueId().toString())) {
                    OProtection.plugin.saveDefaultConfig();
                    OProtection.plugin.reloadConfig();
                    sender.sendMessage(OProtection.plugin.color(prefix + reloaded));
                } else {
                    sender.sendMessage(OProtection.plugin.color(prefix + noPerms));
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}
