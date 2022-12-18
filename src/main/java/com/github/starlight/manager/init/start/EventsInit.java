package com.github.starlight.manager.init.start;

import com.github.starlight.chect.Check;
import com.github.starlight.event.UIEvent;
import com.github.starlight.manager.init.Initable;

public class EventsInit implements Initable {
    /**
     * 当插件启动完成时要执行的操作
     * 通常用来注册事件监听器
     * @see Initable
     * @implNote Start()方法
     * <p><font color="red" >不建议第三方开发者调用，有可能会破坏接口</font>
     */
    public void Start(){
        Check.ChectEnable();
        UIEvent.UIEventEnable();
    }
}
