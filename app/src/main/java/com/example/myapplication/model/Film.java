package com.example.myapplication.model;

import android.graphics.Bitmap;

import java.util.Date;

public class Film {
    private String name;
    private Date dateSortie;
    private Bitmap image;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Film() {
        this.name = "default";
        this.dateSortie = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getMegaDate() {
        return "Date de Sortie : "
                + this.dateSortie.getYear()
                + "-" + (this.dateSortie.getMonth() < 10 ? "0" + this.dateSortie.getMonth(): this.dateSortie.getMonth())
                + "-" + (this.dateSortie.getDay() < 10 ? "0" + this.dateSortie.getDay(): this.dateSortie.getDay());
    }
}
