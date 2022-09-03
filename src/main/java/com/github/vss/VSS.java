package com.github.vss;

import org.bukkit.plugin.java.JavaPlugin;

public final class VSS extends JavaPlugin{//不用看这里，这里的代码都在PluginAPI那里

    @Override
    public void onLoad(){
        PluginAPI.INSTANCE.load(this);
    }


    @Override
    public void onEnable() {
        PluginAPI.INSTANCE.start(this);
    }


    @Override
    public void onDisable() {
        PluginAPI.INSTANCE.stop(this);
    }


}
