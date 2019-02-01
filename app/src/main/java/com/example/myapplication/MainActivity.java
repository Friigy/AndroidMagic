package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.controller.ArrayAdapterList;
import com.example.myapplication.async.AsyncTaskRandom;
import com.example.myapplication.model.Film;
import com.example.myapplication.thread.FilmThreadRandom;
import com.example.myapplication.thread.HandlerTasksRandom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    public ArrayAdapterList arrayAdapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listFilmView = (ListView) findViewById(R.id.listFilm);

        final List<Film> listFilm = new ArrayList<Film>();

        arrayAdapterList = new ArrayAdapterList(this, R.layout.content_main, listFilm);

        Film film1 = new Film();
        film1.setName("Die Hard");
        film1.setDateSortie(new Date(1988, 7, 20));
        Film film2 = new Film();
        film2.setName("Die Hard 2");
        film2.setDateSortie(new Date(1990, 7, 3));
        Film film3 = new Film();
        film3.setName("Die Hard with a Vengeance");
        film3.setDateSortie(new Date(1995, 5, 19));

        listFilm.add(film1);
        listFilm.add(film2);
        listFilm.add(film3);

        listFilmView.setAdapter(arrayAdapterList);
        Button randomAsyncButton = (Button) findViewById(R.id.randomAsyncButton);
        Button randomThreadButton = (Button) findViewById(R.id.randomThreadButton);

        View.OnClickListener listenerAsyncRandomFilm = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExecutorService service =  Executors.newFixedThreadPool(5);
                for(Film film: listFilm) {
                    AsyncTaskRandom atf = new AsyncTaskRandom(MainActivity.this);
                    atf.executeOnExecutor(service, film);
                }
            }
        };
        randomAsyncButton.setOnClickListener(listenerAsyncRandomFilm);

        View.OnClickListener listenerThreadRandomFilm = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HandlerTasksRandom handlerThread = new HandlerTasksRandom("ALLO");
                handlerThread.start();
                for (Film film: listFilm) {
                    handlerThread.onLooperPrepared();
                    handlerThread.postTask(new FilmThreadRandom(film, MainActivity.this));
                }
            }
        };
        randomThreadButton.setOnClickListener(listenerThreadRandomFilm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
