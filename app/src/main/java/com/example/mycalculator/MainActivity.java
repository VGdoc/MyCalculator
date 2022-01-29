package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private final Calc calc = new Calc();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        init();
        setListeners();
    }

    public void init() {
        for (int i = 0; i < Calc.ExistButtons.values().length; i++){
            calc.setButton(findViewById(Calc.ExistButtons.values()[i].getLayoutID()),i);
        }
        calc.setSummaries(findViewById(R.id.summaries));
    }

    public void setListeners(){
        calc.setListeners();
    }



}















































