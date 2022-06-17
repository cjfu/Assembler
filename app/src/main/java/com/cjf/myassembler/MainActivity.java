package com.cjf.myassembler;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.cjf.assemler.Assembler;
import com.cjf.assemler.AssemblerImpl;
import com.cjf.assemler.DataStream;
import com.cjf.assemler.Option;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Assembler assembler = new Assembler();
        assembler.setOption(new Option<String>() {
            @Override
            public void onOption(AssemblerImpl self, DataStream<String> dataStream) {
                super.onOption(self, dataStream);
                String str = dataStream.get();
                Log.e(TAG, "onOption1: " + str);
                self.send(new DataStream<Integer>().set(Integer.parseInt(str + "0")));
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
            }
        });

        Assembler assembler2 = new Assembler();
        assembler2.setOption(new Option<Integer>() {
            @Override
            public void onOption(AssemblerImpl self, DataStream<Integer> dataStream) {
                int num = dataStream.get();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            Log.e(TAG, "onOption2: " + num);
                            self.send(new DataStream<Float>().set(num + 0.5f));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
                e.printStackTrace();
            }
        });

        Assembler assembler3 = new Assembler();
        assembler3.setOption(new Option<Float>() {
            @Override
            public void onOption(AssemblerImpl self, DataStream<Float> dataStream) {
                super.onOption(self, dataStream);
                Log.e(TAG, "onOption3: " + dataStream.get());
                self.send(new DataStream<String>().set("11111"));
            }
        });


        assembler.next(assembler2).next(assembler3);


        assembler.receive(new DataStream<String>().set("1"));
        assembler.receive(new DataStream<String>().set("2"));
        assembler.receive(new DataStream<String>().set("3"));
        assembler.receive(new DataStream<String>().set("4"));
    }
}