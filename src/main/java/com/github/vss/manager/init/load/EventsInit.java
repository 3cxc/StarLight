package com.github.vss.manager.init.load;

import com.github.vss.PluginAPI;
import com.github.vss.chect.Check;
import com.github.vss.manager.ConfigManager;
import com.github.vss.manager.init.Initable;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class EventsInit implements Initable {
    public void Start(){
        JavaPlugin plugin = PluginAPI.INSTANCE.getPlugin();
        plugin.getLogger().info(ChatColor.GREEN+ConfigManager.LoadMessage);
        ConfigManager configManager = new ConfigManager();
        configManager.Configload();
        Check.ChectEnable();
    }
}
