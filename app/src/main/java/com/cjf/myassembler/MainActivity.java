package com.cjf.myassembler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.cjf.assemler.Assembler;
import com.cjf.assemler.AssemblerImpl;
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
                .setWorker((OnOption<String>) (self, dataStream) -> {
                    Log.e(TAG, "onOption1: " + dataStream.get());
                    self.send(new DataStream<Integer>().set(2));
                })
                .next(new Assembler())
                .setWorker((OnOption<Integer>) (self, dataStream) -> {
                    Log.e(TAG, "onOption2: " + dataStream.get());
                    self.send(new DataStream<Float>().set(12.1f));
                })
                .next(new Assembler())
                .setWorker((OnOption<Float>) (self, dataStream) -> {
                    Log.e(TAG, "onOption3: " + dataStream.get());
                });

        assembler.receive(new DataStream<String>().set("1"));
    }
}