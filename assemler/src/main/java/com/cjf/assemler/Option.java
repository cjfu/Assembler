package com.cjf.assemler;

public class Option<T> implements OnOption<T> {

    @Override
    public void onOption(AssemblerImpl self, DataStream<T> dataStream) {

    }

    public void onError(Exception e) {

    }
}
