package com.example.pennryan.myapplication;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pennryan.myapplication.Model.Album;
import com.example.pennryan.myapplication.Model.Song;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DataViewActivity extends Activity {

    @Bind(R.id.btn)
    Button btn;

    @Bind(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view);
        ButterKnife.bind(this);

        SQLiteDatabase db = Connector.getDatabase();

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
        song2.setName("song2");;
        song2.setDuration(356);
        song2.setAlbum(album);
        song2.save();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Song song = DataSupport.find(Song.class, 1);
                List<Song> songs = DataSupport.where("name like ?", "song%").order("duration").find(Song.class);
                if (songs!=null && !songs.isEmpty()){
                    tv.setText(songs.get(0).getName());
                }
            }
        });
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
