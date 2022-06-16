package com.cjf.assemler;

public class Assembler implements AssemblerImpl {

    DataStream sourceData;
    AssemblerImpl next;
    OnOption<?> onOption;

    @Override
    public AssemblerImpl next(AssemblerImpl next) {
        this.next = next;
        return next;
    }

    @Override
    public void receive(DataStream<?> data) {
        this.sourceData = data;
        work();
    }

    @Override
    public AssemblerImpl setOption(OnOption<?> onOption) {
        this.onOption = onOption;
        return this;
    }

    public void work() {
        if (onOption != null) {
            onOption.onOption(this, sourceData);
        }
    }

    @Override
    public void send(DataStream<?> resultData) {
        if (next != null) {
            next.receive(resultData);
        } else {
            //到达最后一层
        }
    }

}