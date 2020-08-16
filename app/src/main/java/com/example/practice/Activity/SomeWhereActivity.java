package com.example.practice.Activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import java.util.List;

public class SomeWhereActivity extends AppCompatActivity {

    ImageView imageView;
    boolean flg = true;
    ProgressBar progressBar;
    int percent;
    private SomeWhereActivityViewModel someWhereActivityViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_some_where);
        imageView = findViewById(R.id.imageView2);
        progressBar = findViewById(R.id.progressBar2);

        Spinner spinner = findViewById(R.id.spinner);


        TextView txtCount = findViewById(R.id.txt_count);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        EditText eTxtItem = findViewById(R.id.eTxtTodo);
        Button button = findViewById(R.id.button3);
        ImageButton imageButton = findViewById(R.id.imageButton);

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

            //        percent = 50;
//        progressBar.setProgress(percent);
        Animator animation = ObjectAnimator.ofInt(progressBar,"progress",95);
        animation.setDuration(2000); // 1秒間でアニメーションする
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();


        URL url = null;

        try {

            url = new URL("https://i.ibb.co/L0gS5rZ/room-pic.png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        new getImageAsync().execute(url);

        //Bitmap bitmap = getRemoteImage(url);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todo = eTxtItem.getText().toString();
                DbTable dbTable = new DbTable();
                dbTable.item = todo;
                someWhereActivityViewModel.insertNewItem(dbTable);
                eTxtItem.getText().clear();

            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        someWhereActivityViewModel.getItems().observe(this, dbTables1 -> {
            itemAdapter.setDate(dbTables1);
            itemAdapter.notifyDataSetChanged();
        });

        someWhereActivityViewModel.getItemCount().observe(this, integer -> txtCount.setText(String.valueOf(integer)));


    }

    public Bitmap getRemoteImage(URL imageURL) {

        try {
            if (flg) {
                progressBar.setVisibility(android.widget.ProgressBar.VISIBLE);
                flg = false;
            }
            else {
                progressBar.setVisibility(android.widget.ProgressBar.INVISIBLE);
                flg = true;
            }
            URLConnection urlConnection = imageURL.openConnection();
            urlConnection.connect();

            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());

            Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);

            bufferedInputStream.close();

            return bitmap;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private class getImageAsync extends AsyncTask<URL, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(URL... urls) {

            URL url = urls[0];
            Bitmap bitmap = getRemoteImage(url);

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
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