package com.example.pennryan.myapplication;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pennryan.myapplication.Model.Album;
import com.example.pennryan.myapplication.Model.Song;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DataViewActivity extends Activity {

    SQLiteDatabase db;

    @Bind(R.id.tv)
    TextView tv;

    @Bind(R.id.lv)
    ListView lv;

    @OnClick(R.id.btn_add)
    void Add() {

        Random r = new Random(100);
        int i = r.nextInt();

        Song s = new Song();
        s.setName("song" + i);
        s.setDuration(i);
        //s.setAlbum(album);
        s.save();

        tv.setText(s.getName() + " add.");
    }

    @OnClick(R.id.btn_update)
    void Update() {
        Song s = DataSupport.find(Song.class, 1);
        s.setDuration(new Random(100).nextInt());
        s.save();

        tv.setText(s.getName() + " Update: " + s.getDuration());
    }

    @OnClick(R.id.btn_delete)
    void Delete(){
        int id = new Random(100).nextInt();
        int i = DataSupport.delete(Song.class, id);
        if (i>0){
            tv.setText( i + " Deleted.");
        }else {
            tv.setText(i + " Not exist.");
        }
    }

    @OnClick(R.id.btn_query)
    void Query() {
        List<Song> songs = DataSupport.where("name like ?", "song%").order("duration").find(Song.class);
        if (songs != null && !songs.isEmpty()) {

            lv.setAdapter(new ArrayAdapter<Song>(this,
                    android.R.layout.simple_list_item_1, songs));
            lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @OnClick(R.id.btn_count)
    void Count() {
        int count = DataSupport.count(Song.class);
        tv.setText("Count: " + count);
    }

    @OnClick(R.id.btn_sum)
    void Sum() {
        int sum = DataSupport.sum(Song.class, "duration", int.class);
        tv.setText("Sum: " + sum);
    }

    @OnClick(R.id.btn_average)
    void Average() {
        double avg = DataSupport.average(Song.class, "duration");
        tv.setText("Average: " + avg);
    }

    @OnClick(R.id.btn_max)
    void Max() {
        int max = DataSupport.max(Song.class, "duration", int.class);
        tv.setText("Max: " + max);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view);
        ButterKnife.bind(this);

        db = Connector.getDatabase();

        Album album = new Album();
        album.setName("album");
        album.setPrice(10.99f);
        album.save();

        Song song1 = new Song();
        song1.setName("song1");
        song1.setDuration(320);
        song1.setAlbum(album);
        song1.save();

        Song song2 = new Song();
        song2.setName("song2");
        song2.setDuration(356);
        song2.setAlbum(album);
        song2.save();

        Query();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_data_view, menu);
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
