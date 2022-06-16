package com.cjf.assemler;

public interface AssemblerImpl {

    AssemblerImpl next(AssemblerImpl next);//组装动作，将不同的组件组装成链式
    void receive(DataStream<?> data);//受理数据。用于接受数据并触发数据转化操作
    AssemblerImpl setOption(OnOption<?> onOption);//用于插入数据操作逻辑
    void send(DataStream<?> resultData);//输出处理后的信息
}
