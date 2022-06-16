package com.cjf.myassembler;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.cjf.assemler.Assembler;
import com.cjf.assemler.DataStream;
import com.cjf.assemler.OnOption;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Assembler assembler = new Assembler();

        assembler
                .setOption((OnOption<String>) (self, dataStream) -> {
                    String str = dataStream.get();
                    Log.e(TAG, "onOption1: " + str);
                    self.send(new DataStream<Integer>().set(Integer.parseInt(str + "0")));
                })
                .next(new Assembler())
                .setOption((OnOption<Integer>) (self, dataStream) -> {
                    int num = dataStream.get();
                    Log.e(TAG, "onOption2: " + num);
                    self.send(new DataStream<Float>().set(num + 0.5f));
                })
                .next(new Assembler())
                .setOption((OnOption<Float>) (self, dataStream) -> {
                    Log.e(TAG, "onOption3: " + dataStream.get());
                });

        assembler.receive(new DataStream<String>().set("1"));
    }
}