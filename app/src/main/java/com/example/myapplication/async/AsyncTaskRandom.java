package com.example.myapplication.async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.myapplication.MainActivity;
import com.example.myapplication.model.Film;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AsyncTaskRandom extends AsyncTask<Film, Object, Object> {
    private MainActivity mainActivity;

    public AsyncTaskRandom(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected Object doInBackground(Film... objects) {
        URL url = null;
        Bitmap bitmap;

        try {
            url = new URL("https://picsum.photos/200/200/?random");
            URLConnection urlConn = url.openConnection();
            bitmap = BitmapFactory.decodeStream(urlConn.getInputStream());
            objects[0].setImage(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(Object o) {
        mainActivity.arrayAdapterList.notifyDataSetChanged();
    }
}
