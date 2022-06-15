package com.cjf.myassembler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cjf.assemler.Assembler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Assembler assembler = new Assembler();
    }
}