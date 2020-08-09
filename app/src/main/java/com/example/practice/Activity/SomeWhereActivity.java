package com.example.practice.Activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practice.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SomeWhereActivity extends AppCompatActivity {

    ImageView imageView;
    boolean flg = true;
    ProgressBar progressBar;
    int percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_some_where);
        imageView = findViewById(R.id.imageView2);
        progressBar = findViewById(R.id.progressBar2);
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