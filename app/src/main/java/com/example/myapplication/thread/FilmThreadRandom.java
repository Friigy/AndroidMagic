package com.example.myapplication.thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.MainActivity;
import com.example.myapplication.model.Film;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FilmThreadRandom extends Thread {
    private Film film;
    private MainActivity mainActivity;

    public FilmThreadRandom(Film film, MainActivity mainActivity) {
        this.film = film;
        this.mainActivity = mainActivity;
    }

    public void run() {
        URL url = null;
        Bitmap bitmap;

        try {
            url = new URL("https://picsum.photos/200/200/?random");
            URLConnection urlConn = url.openConnection();
            bitmap = BitmapFactory.decodeStream(urlConn.getInputStream());
            film.setImage(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainActivity.arrayAdapterList.notifyDataSetChanged();
            }
        });
    }
}
