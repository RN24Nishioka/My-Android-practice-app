package com.example.practice.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practice.R;

import java.util.Random;

public class NextPage extends AppCompatActivity {

    Button btnReflect;
    TextView txtOutput;
    TextView txtOutput2;
    EditText eTxtInput;
    ProgressBar progressBar;
    boolean flg = true;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_page);

        btnReflect = findViewById(R.id.btnDisplay);
        txtOutput = findViewById(R.id.txtViewDisplay);
        txtOutput2 = findViewById(R.id.txtViewDisplay2);
        eTxtInput = findViewById(R.id.etxt_input);
        progressBar = findViewById(R.id.progressBar);



        btnReflect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flg) {
                    progressBar.setVisibility(android.widget.ProgressBar.VISIBLE);
                    flg = false;
                }
                else {
                    progressBar.setVisibility(android.widget.ProgressBar.INVISIBLE);
                    flg = true;
                }
                String message = eTxtInput.getText().toString();
                txtOutput.setText(message);
                new GetOutputInBackground().execute(message);



            }

        });


    }


    public String voiceFollowers() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "そーだそーだ！！";
    }

    public String voiceFollowers2() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "俺もそう思うぞ！！";
    }

    public String voiceFollowers3() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "おっしゃるとおりだ！！";
    }

    private class GetOutputInBackground extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String message = strings[0];
            String followers = voiceFollowers();
            String followers2 = voiceFollowers2();
            String followers3 = voiceFollowers3();
            String output = "";

            Random random = new Random();
            int randomValue = random.nextInt(3);

            switch (randomValue) {
                case 0:
                    output = followers;
                    break;

                case 1:
                    output = followers2;
                    break;

                case 2:
                    output = followers3;
                    break;

            }
            return output;
        }

        @Override
        protected void onPostExecute(String s) {
            txtOutput2.setText(s);
            //asyncTaskは別のスレッドだからここにもprogressbarの表示非表示書かないと文字が表示されるのと同タイミングでは消えない。
            if (flg) {
                progressBar.setVisibility(android.widget.ProgressBar.VISIBLE);
                flg = false;
            }
            else {
                progressBar.setVisibility(android.widget.ProgressBar.INVISIBLE);
                flg = true;
            }
        }
    }

}


