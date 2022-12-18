package com.github.starlight;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * 插件启动部分
 *
 * @see StarLightAPI
 */
public final class StarLight extends JavaPlugin{//不用看这里，这里的代码都在PluginAPI那里

    @Override
    public void onLoad(){
        StarLightAPI.INSTANCE.load(this);
    }


    @Override
    public void onEnable() {
        StarLightAPI.INSTANCE.start(this);
    }


    @Override
    public void onDisable() {
        StarLightAPI.INSTANCE.stop(this);
    }


}
