package com.github.starlight.event;

import com.github.starlight.StarLightAPI;
import com.github.starlight.event.type.MainUIEvent;
import com.github.starlight.event.type.SittingUIEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class UIEvent {
    /**
     * 注册监听器事件
     * @see com.github.starlight.event.type.MainUIEvent
     * @see com.github.starlight.event.type.SittingUIEvent
     * @version 2.0
     * @author 3cxc
     * @since 3.6.0
     */
    public static void UIEventEnable(){
        JavaPlugin plugin = StarLightAPI.INSTANCE.getPlugin();
        plugin.getServer().getPluginManager().registerEvents(new MainUIEvent(),plugin);
        plugin.getServer().getPluginManager().registerEvents(new SittingUIEvent(),plugin);
    }
}
