package com.example.practice.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.R;

public class MainActivity extends AppCompatActivity {


    int count = 0;
    int number = 1;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);

        final TextView textView = findViewById(R.id.textView);
        final Button addButton = findViewById(R.id.button);
        final Button minusButton = findViewById(R.id.button2);
        final ImageView myImageView = findViewById(R.id.imageView);
        Spinner mySpinner = findViewById(R.id.spinner);

        //String[] nums = (String[]) R.array.numbers_array;//これ出来なかった
        String[] nums = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
//追加された箇所
        NumAdapter numAdapter = new NumAdapter(nums, new NumAdapter.Listener() {
            @Override
            public void onClick(int number) {
                Button addButton = findViewById(R.id.button);
                Button minusButton = findViewById(R.id.button2);

                Toast.makeText(MainActivity.this, Integer.toString(number), Toast.LENGTH_SHORT).show();
                addButton.setText("+" + number);
                minusButton.setText("-" + number);
                MainActivity.this.number = number;

                if (number <= 3) {
                    myImageView.setImageResource(R.drawable.ic_baseline_directions_walk_96);
                } else if (number >= 4 && number <= 7) {
                    myImageView.setImageResource(R.drawable.ic_baseline_directions_run_96);
                } else {
                    myImageView.setImageResource(R.drawable.ic_baseline_directions_bike_96);
                }
            }
        });
        recyclerView.setAdapter(numAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mySpinner.setAdapter(adapter);


        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toNextPage:
                Toast.makeText(this, "nextPage", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, NextPage.class);
                startActivity(intent);

                return true;

            case R.id.toSomeWhere:
                Toast.makeText(this, "somewhere", Toast.LENGTH_SHORT).show();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        final Button addButton = findViewById(R.id.button);
        final Button minusButton = findViewById(R.id.button2);

        if (view.getId() == R.id.cbxMinus) {
            if (checked) {
                minusButton.setVisibility(View.VISIBLE);
            } else if (minusButton.getVisibility() != View.INVISIBLE) {
                minusButton.setVisibility(View.INVISIBLE);
            }
        } else if (view.getId() == R.id.cbxAdd) {
            if (checked) {
                addButton.setVisibility(View.VISIBLE);

            } else if (addButton.getVisibility() != View.INVISIBLE) {
                addButton.setVisibility(View.INVISIBLE);
            }
        }


    }


}
