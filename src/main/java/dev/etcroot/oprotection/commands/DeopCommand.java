package dev.etcroot.oprotection.commands;

// Bukkit Libs
import dev.etcroot.oprotection.OProtection;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

// Java Lib's
import java.util.List;

public class DeopCommand implements CommandExecutor {
    Configuration conf;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<String> allowedOps = OProtection.cfg.getStringList("allowed-op");
        String prefix = OProtection.cfg.getString("prefix");
        String noPerms = OProtection.cfg.getString("no-perms");
        String noName = OProtection.cfg.getString("no-username");
        String notOnline = OProtection.cfg.getString("not-online");
        String remPlayer = OProtection.cfg.getString("removed-player");
        String addPlayer = OProtection.cfg.getString("added-player");
        String notInList = OProtection.cfg.getString("not-in-list");
        Player p = (Player) sender;
        if(sender instanceof Player) {
            if(allowedOps.contains(p.getUniqueId().toString())) {
                if(args.length == 0) {
                    sender.sendMessage(OProtection.plugin.color(prefix + noName));
                } else {
                    Player argPlayer = Bukkit.getPlayer(args[0]);
                    if (argPlayer == null) {
                        sender.sendMessage(OProtection.plugin.color(prefix + notOnline)); // sending usage
                    }
                    if (allowedOps.contains(argPlayer.getUniqueId().toString())) {
                        if(argPlayer.isOp()) {
                            argPlayer.setOp(false);
                            sender.sendMessage(OProtection.plugin.color(prefix + remPlayer.replaceAll("%player%", argPlayer.getDisplayName())));
                            if(allowedOps.contains(argPlayer.getUniqueId().toString())) {
                                List<String> list = OProtection.cfg.getStringList("allowed-op");
                                list.remove(argPlayer.getUniqueId().toString());
                                OProtection.cfg.set("allowed-op", list);
                                OProtection.plugin.saveConfig();
                                OProtection.plugin.reloadConfig();
                            }
                        } else {
                            sender.sendMessage(OProtection.plugin.color(prefix + "&cThis player is not op."));
                        }
                    } else {
                        sender.sendMessage(OProtection.plugin.color(prefix + notInList));
                    }
                }
            } else {
                sender.sendMessage(OProtection.plugin.color(prefix + noPerms));
            }
        } else {
            sender.sendMessage(OProtection.plugin.color(prefix + "&cThis command cannot be ran from console."));
        }
        return true;
    }
}
