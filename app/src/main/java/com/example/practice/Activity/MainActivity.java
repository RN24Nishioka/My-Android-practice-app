package com.example.practice.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practice.R;

public class MainActivity extends AppCompatActivity {

    int count = 0;
    int number = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.textView);
        final Button addButton = findViewById(R.id.button);
        final Button minusButton = findViewById(R.id.button2);
        Spinner mySpinner = findViewById(R.id.spinner);
        final ImageView myImageView = findViewById(R.id.imageView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //ここから
                String selectNumbers = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, selectNumbers, Toast.LENGTH_LONG);
                number = Integer.parseInt(selectNumbers);
                addButton.setText("+" + number);
                minusButton.setText("-" + number);
                //minusButton.setText(String.valueOf(number));

                if (number <= 3) {
                    myImageView.setImageResource(R.drawable.ic_baseline_directions_walk_96);
                } else if (number >= 4 && number <= 7) {
                    myImageView.setImageResource(R.drawable.ic_baseline_directions_run_96);
                } else {
                    myImageView.setImageResource(R.drawable.ic_baseline_directions_bike_96);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count += number;
                if (count % 10 != 0) {
                    textView.setText(String.valueOf(count));

                } else {
                    textView.setText(count + "\n十の倍数！");
                }


            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count -= number;
                if (count % 10 != 0) {
                    textView.setText(String.valueOf(count));
                } else {
                    textView.setText(count + "\n十の倍数！");
                }
            }
        });
    }

}
