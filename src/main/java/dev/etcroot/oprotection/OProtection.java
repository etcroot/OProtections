package dev.etcroot.oprotection;

import dev.etcroot.oprotection.commands.DeopCommand;
import dev.etcroot.oprotection.commands.OpCommand;
import dev.etcroot.oprotection.commands.ReloadCommand;
import dev.etcroot.oprotection.listeners.BlockMcOP;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class OProtection extends JavaPlugin {

    public static OProtection plugin;
    public static FileConfiguration cfg;
    public String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BlockMcOP(), this);
        // Import Commands
        getCommand("opreload").setExecutor(new ReloadCommand());
        getCommand("op").setExecutor(new OpCommand());
        getCommand("deop").setExecutor(new DeopCommand());
        // Other stuff
        saveDefaultConfig();
        plugin = this;
        cfg = this.getConfig();
    }

    @Override
    public void onDisable() {
        saveDefaultConfig();
    }
}
