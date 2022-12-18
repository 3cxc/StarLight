package com.github.starlight.chect;

import com.github.starlight.StarLightAPI;
//import com.github.vss.chect.type.FlyCheck;
import com.github.starlight.chect.type.FlyCheck;
import com.github.starlight.chect.type.SecurityAlert;
import com.github.starlight.chect.type.SpeedCheck;
import org.bukkit.plugin.java.JavaPlugin;

public class Check{
    /**
     * 注册监听器事件
     * @version 2.0
     * @author 3cxc
     * @since 3.6.0
     */
    public static void ChectEnable(){
        JavaPlugin plugin = StarLightAPI.INSTANCE.getPlugin();
        plugin.getServer().getPluginManager().registerEvents(new SpeedCheck(),plugin);
        plugin.getServer().getPluginManager().registerEvents(new FlyCheck(),plugin);
        plugin.getServer().getPluginManager().registerEvents(new SecurityAlert(),plugin);
    }
}
