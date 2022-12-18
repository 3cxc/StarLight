package com.github.starlight.manager.init.stop;

import com.github.starlight.StarLightAPI;
import com.github.starlight.manager.ConfigManager;
import com.github.starlight.manager.init.Initable;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import static com.github.starlight.manager.ConfigManager.PlayerList;
import static com.github.starlight.manager.ConfigManager.config;

public class TerminateEvents implements Initable {
    /**
     * 当插件停止时要执行的操作
     * @see Initable
     * @implNote Start()方法
     * <p><font color="red" >不建议第三方开发者调用，有可能会破坏接口</font>
     */
    public void Start(){
        JavaPlugin plugin = StarLightAPI.INSTANCE.getPlugin();
        config.getConfig().set("SecurityAlert.Whitelist",PlayerList);
        config.saveConfig();
        config.reloadConfig();
        plugin.getLogger().info(ChatColor.GREEN+ConfigManager.DisableMessage);
    }
}
