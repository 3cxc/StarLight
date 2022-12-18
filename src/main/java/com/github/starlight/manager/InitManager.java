package com.github.starlight.manager;

import com.github.starlight.manager.init.Initable;
import com.github.starlight.manager.init.start.EventsInit;
import com.github.starlight.manager.init.start.BStats;
import com.github.starlight.manager.init.start.CommandRegister;
import com.github.starlight.manager.init.stop.TerminateEvents;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.ImmutableClassToInstanceMap;

public class InitManager {
    ClassToInstanceMap<Initable> initableClassToInstanceMapStart;
    ClassToInstanceMap<Initable> initableClassToInstanceMapStop;



    public InitManager(){//读取start,stop两个事件
        initableClassToInstanceMapStart =  new ImmutableClassToInstanceMap.Builder<Initable>()
                .put(EventsInit.class, new EventsInit())
                .put(BStats.class, new BStats())
                .put(CommandRegister.class, new CommandRegister())
                .build();
        initableClassToInstanceMapStop =  new ImmutableClassToInstanceMap.Builder<Initable>()
                .put(TerminateEvents.class, new TerminateEvents())
                .build();
    }

    public void start(){//启动
        for (Initable initable : this.initableClassToInstanceMapStart.values()){
            initable.Start();
        }
    }

    public void stop(){//关闭
        for (Initable initable : this.initableClassToInstanceMapStop.values()){
            initable.Start();
        }
    }
}
