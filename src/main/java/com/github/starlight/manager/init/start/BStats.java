package com.github.starlight.manager.init.start;

import com.github.starlight.StarLightAPI;
import com.github.starlight.manager.init.Initable;
import com.github.starlight.shaded.Metrics;

public class BStats implements Initable {
    /**
     * 当插件启动完成时要执行的操作
     * 用以注册BStats(插件使用量统计)
     * @see Initable
     * @implNote Start()方法
     * <p><font color="red" >第三方开发者无需调用此类</font>
     */
    public void Start() {
        int pluginId = 12820; //你的ID
        try {
            Metrics metrics = new Metrics(StarLightAPI.INSTANCE.getPlugin(), pluginId);
        } catch (Exception exception) {}
    }
}
