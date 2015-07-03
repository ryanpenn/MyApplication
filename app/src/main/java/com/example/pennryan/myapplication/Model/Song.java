package com.example.pennryan.myapplication.Model;

import org.litepal.crud.DataSupport;

/**
 * Created by pennryan on 15/7/3.
 */
public class Song extends DataSupport {

    private String name;

    private int duration;

    private Album album;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}