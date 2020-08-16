package com.example.practice.Activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SomeWhereActivity extends AppCompatActivity /*implements DatePickerDialog.OnDateSetListener*/   {

    ImageView imageView;
    boolean flg = true;
    int percent;
    private SomeWhereActivityViewModel someWhereActivityViewModel;
    String selectedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_some_where);



        TextView txtCount = findViewById(R.id.txt_count);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        EditText eTxtItem = findViewById(R.id.eTxtTodo);
        Button button = findViewById(R.id.button3);
        Button dateButton = findViewById(R.id.dateButton);


        someWhereActivityViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory(){


            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new SomeWhereActivityViewModel(getApplication());
            } }).get(SomeWhereActivityViewModel.class);

        List<DbTable> dbTables = new ArrayList<>();
        ItemAdapter itemAdapter = new ItemAdapter(dbTables);

        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);






        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar date = Calendar.getInstance();

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        SomeWhereActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                //setした日付を取得して表示
                                selectedDate = String.format("%d / %02d / %02d", year, month+1, dayOfMonth);
                                dateButton.setText(selectedDate);

                            }
                        },
                        date.get(Calendar.YEAR),
                        date.get(Calendar.MONTH),
                        date.get(Calendar.DATE)
                );

                //dialogを表示
                datePickerDialog.show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todo = eTxtItem.getText().toString();
                DbTable dbTable = new DbTable();
                dbTable.item = todo;
                dbTable.date = selectedDate;
                someWhereActivityViewModel.insertNewItem(dbTable);
                eTxtItem.getText().clear();


            }
        });




        someWhereActivityViewModel.getItems().observe(this, dbTables1 -> {
            itemAdapter.setDate(dbTables1);
            itemAdapter.notifyDataSetChanged();
        });

        someWhereActivityViewModel.getItemCount().observe(this, integer -> txtCount.setText(String.valueOf(integer)));


    }


}