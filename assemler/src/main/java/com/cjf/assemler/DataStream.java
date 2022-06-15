package com.cjf.assemler;

public class DataStream<T> {
    private T data;

    public T get() {
        return data;
    }

    public DataStream<T> set(T data) {
        this.data = data;
        return this;
    }
}
