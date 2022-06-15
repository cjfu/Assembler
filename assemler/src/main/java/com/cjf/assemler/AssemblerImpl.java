package com.cjf.assemler;

public interface AssemblerImpl {

    AssemblerImpl next(AssemblerImpl next);
    void receive(DataStream<?> data);
    AssemblerImpl setWorker(OnOption<?> onOption);
    void work();//处理信息
    void send(DataStream<?> resultData);//输出处理后的信息
}
