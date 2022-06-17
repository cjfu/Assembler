package com.cjf.assemler;

import java.util.ArrayList;
import java.util.List;

public class Assembler implements AssemblerImpl {

    List<DataStream> sourceDataList = new ArrayList<>();
    AssemblerImpl next;
    Option<?> onOption;
    int maxTask = 2;

    @Override
    public AssemblerImpl next(AssemblerImpl next) {
        this.next = next;
        return next;
    }

    @Override
    public void receive(DataStream<?> data) {
        synchronized (this) {
            if (sourceDataList.size() == 0) {//当前空闲
                sourceDataList.add(data);
                work();
            } else if (sourceDataList.size() >= maxTask) {
                //超出任务最大数量
                onOption.onError(new MaxTaskException());
            } else {//当前不空闲且可排队
                sourceDataList.add(data);
            }
        }
    }

    @Override
    public AssemblerImpl setOption(Option<?> onOption) {
        this.onOption = onOption;
        return this;
    }

    public void work() {
        if (onOption != null && sourceDataList.size() > 0) {
            try {
                onOption.onOption(this, sourceDataList.get(0));
            } catch (Exception e) {
                onOption.onError(e);
            }
        }
    }

    @Override
    public void send(DataStream<?> resultData) {
        synchronized (this) {
            if (sourceDataList.size() > 0) {
                sourceDataList.remove(0);
                if (sourceDataList.size() > 0) {
                    work();
                }
            }
            if (next != null) {
                next.receive(resultData);
            } else {
                //到达最后一层
            }
        }
    }
}