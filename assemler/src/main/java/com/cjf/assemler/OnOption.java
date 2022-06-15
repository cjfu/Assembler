package com.cjf.assemler;

public interface OnOption<T>{
    void onOption(AssemblerImpl self,DataStream<T> dataStream);
}
