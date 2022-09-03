package com.github.vss.manager.init.stop;

import com.github.vss.PluginAPI;
import com.github.vss.manager.ConfigManager;
import com.github.vss.manager.init.Initable;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class TerminateEvents implements Initable {
    public void Start(){
        JavaPlugin plugin = PluginAPI.INSTANCE.getPlugin();
        plugin.getLogger().info(ChatColor.GREEN+ConfigManager.DisableMessage);
    }
}
