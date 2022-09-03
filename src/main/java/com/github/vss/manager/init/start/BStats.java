package com.github.vss.manager.init.start;

import com.github.vss.PluginAPI;
import com.github.vss.manager.init.Initable;
import com.github.vss.shaded.Metrics;

public class BStats implements Initable {
    public void Start() {
        int pluginId = 12820; //你的ID
        try {
            Metrics metrics = new Metrics(PluginAPI.INSTANCE.getPlugin(), pluginId);
        } catch (Exception exception) {}
    }
}
