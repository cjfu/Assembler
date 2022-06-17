package com.cjf.assemler;

public class MaxTaskException extends Exception {

    MaxTaskException(){
        super("超出最大任务限制");
    }
}
