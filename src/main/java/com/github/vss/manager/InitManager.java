package com.github.vss.manager;

import com.github.vss.manager.init.Initable;
import com.github.vss.manager.init.load.EventsInit;
import com.github.vss.manager.init.start.BStats;
import com.github.vss.manager.init.start.CommandRegister;
import com.github.vss.manager.init.stop.TerminateEvents;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.ImmutableClassToInstanceMap;

public class InitManager {
    ClassToInstanceMap<Initable> initableClassToInstanceMapLoad;
    ClassToInstanceMap<Initable> initableClassToInstanceMapStart;
    ClassToInstanceMap<Initable> initableClassToInstanceMapStop;



    public InitManager(){//读取load,start,stop三个事件
        initableClassToInstanceMapLoad =  new ImmutableClassToInstanceMap.Builder<Initable>()
                .put(EventsInit.class, new EventsInit())
                .build();
        initableClassToInstanceMapStart =  new ImmutableClassToInstanceMap.Builder<Initable>()
                .put(BStats.class, new BStats())
                .put(CommandRegister.class, new CommandRegister())
                .build();
        initableClassToInstanceMapStop =  new ImmutableClassToInstanceMap.Builder<Initable>()
                .put(TerminateEvents.class, new TerminateEvents())
                .build();
    }

    public void load(){//加载
        for (Initable initable : this.initableClassToInstanceMapLoad.values()){
            initable.Start();
        }
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
