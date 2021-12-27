package dev.etcroot.oprotection.listeners;

import dev.etcroot.oprotection.OProtection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class BlockMcOP implements Listener {
    @EventHandler
    public void BlockMcOP(PlayerCommandPreprocessEvent e) {
        String prefix = OProtection.cfg.getString("prefix");
        String cmdDisabled = OProtection.cfg.getString("cmd-disabled");
        if(OProtection.cfg.getBoolean("disable-cmd")) {
            if(e.getMessage().contains("minecraft:op")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(OProtection.plugin.color(prefix + cmdDisabled));
            } else if(e.getMessage().contains("minecraft:deop")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(OProtection.plugin.color(prefix + cmdDisabled));
            }
        } else {
            e.setCancelled(false);
        }
    }
}
