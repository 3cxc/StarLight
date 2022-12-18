package com.github.starlight.manager.init;

public interface Initable {
    /**
     * 提供了一个启动方法
     * 用以注册各种事件监听器，命令等
     * @implNote Start()方法
     * <p><font color="red" >不建议第三方开发者调用，有可能会破坏接口</font>
     */
    void Start();
}
