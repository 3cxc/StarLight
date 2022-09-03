package com.github.vss.chect;

import com.github.vss.PluginAPI;
//import com.github.vss.chect.type.FlyCheck;
import com.github.vss.chect.type.SpeedCheck;
import org.bukkit.plugin.java.JavaPlugin;

public class Check{
    public static void ChectEnable(){
        JavaPlugin plugin = PluginAPI.INSTANCE.getPlugin();
        plugin.getServer().getPluginManager().registerEvents(new SpeedCheck(),plugin);
        //Fly存在BUG
        //plugin.getServer().getPluginManager().registerEvents(new FlyCheck(),plugin);
    }
}
