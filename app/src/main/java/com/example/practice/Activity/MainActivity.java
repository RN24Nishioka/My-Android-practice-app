package com.example.practice.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.practice.R;

public class MainActivity extends AppCompatActivity {

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.textView);
        Button addButton = findViewById(R.id.button);
        Button minusButton = findViewById(R.id.button2);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count += 1;
                if(count%10 != 0){
                textView.setText(String.valueOf(count));

                }else{
                textView.setText(count+"\n十の倍数！");}
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count -= 1;
                if(count%10 != 0){
                    textView.setText(String.valueOf(count));
                }else{
                    textView.setText(count+"\n十の倍数！");}
            }
            });
        }

    }
