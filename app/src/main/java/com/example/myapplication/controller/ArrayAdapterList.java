package com.example.myapplication.controller;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Film;

import java.util.List;

public class ArrayAdapterList extends ArrayAdapter {
    public ArrayAdapterList(Context context, int listId, List<Film> filmList) {
        super(context, listId, filmList);
    }

    @Override
    public View getView (int position, View view, ViewGroup parent){
        // Get the data item for this position
        Log.d("ArrayAdapter :", "???? ");

        Film row = (Film) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_view_layout, parent, false);
        }
        // Lookup view for data population
        TextView nom = (TextView) view.findViewById(R.id.filmTitle);
        TextView dateDeSortie = (TextView) view.findViewById(R.id.dateSortieFilm);
        ImageView imageFilm = (ImageView) view.findViewById(R.id.filmImage);
        // Populate the data into the template view using the data object
        nom.setText(row.getName());
        dateDeSortie.setText(row.getMegaDate());
        LinearLayout item = (LinearLayout) view.findViewById(R.id.layoutList);

        /*
        if (position % 2 == 0) {
            item.setBackgroundColor(Color.WHITE);
        } else {
            item.setBackgroundColor(Color.GRAY);
        }
        */

        if (row.getImage() != null) {
            imageFilm.setImageBitmap(row.getImage());
        }

        // Return the completed view to render on screen
        return view;
    }
}
