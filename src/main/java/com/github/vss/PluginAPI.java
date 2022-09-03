package com.github.vss;

import com.github.vss.logger.Logger;
import com.github.vss.manager.ConfigManager;
import com.github.vss.manager.InitManager;
import com.github.vss.update.Update;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public enum PluginAPI {
    INSTANCE;

    private JavaPlugin plugin;

    private InitManager initManager;

    public JavaPlugin getPlugin() {
        return this.plugin;
    }

    public void load(JavaPlugin Plugin){
        this.plugin = Plugin;
        plugin.getConfig().options().copyDefaults();
        plugin.saveDefaultConfig();
        Logger.LOGGER.CreateFolder("Log");//生成Log文件夹
        Logger.LOGGER.CreateFolder("WarningLog");
        Logger.LOGGER.CreateFolder("DeBugLog");
        plugin.saveResource("messages/zh_cn.yml", false);//生成语言文件
        plugin.saveResource("messages/en_us.yml", false);
        this.initManager = new InitManager();
        this.initManager.load();
    }

    public void start(JavaPlugin Plugin){
        this.plugin = Plugin;
        plugin.getLogger().info(ChatColor.GREEN+ConfigManager.EnableMessage);
        UpdateTimer(plugin);
        this.initManager.start();
    }

    public void UpdateTimer(JavaPlugin Plugin){
        this.plugin = Plugin;
        long delay = 20 * 3;//三秒后进行第一次检查
        long period = 20 * 60 * 30;//每隔半小时检查一次更新
        new BukkitRunnable(){
            public void run(){
                Update.SystemUpdate(null);
            }
        }.runTaskTimerAsynchronously(plugin,delay,period);
    }

    public void stop(JavaPlugin Plugin){
        this.plugin = Plugin;
        this.initManager.stop();
    }
}
