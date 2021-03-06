package com.example.pennryan.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    static int step = 0;

    static final String[] strs = new String[] {
        "Layout", "ListView - ArrayAdapter", "ListView - SimpleAdapter", "ListView - BaseAdapter"
            , "MyView", "Database","float Window", "fragment", "dynamic fragments","actionBar"
            , "wechat"
    };

    @Bind(R.id.tv)
    TextView tv;

    @Bind(R.id.lv)
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tv.setText("MyApp");

        lv.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                strs));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:

                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, ArrayListViewActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, SimpleListViewActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, CustomListViewActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, ImageViewActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, DataViewActivity.class));
                        break;
                    case 6:
                        startService(new Intent(MainActivity.this, FloatWindowService.class));
                        finish();
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, FragmentsActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, DynamicFragmentsActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this, ActionBarActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });

        Log.i("LifeCycle", ++step + " : Activity.onCreate");

        Intent intent = new Intent(this, MyService.class);
        startService(intent);
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

    @Override
    protected void onStart() {
        super.onStart();

        Log.i("LifeCycle", ++step + " : Activity.onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("LifeCycle", ++step + " : Activity.onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LifeCycle", ++step + " : Activity.onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LifeCycle", ++step + " : onStop");
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);

        super.onDestroy();
        Log.i("LifeCycle", ++step + " : Activity.onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("LifeCycle", ++step + " : Activity.onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("LifeCycle", ++step + " : Activity.onRestoreInstanceState");
    }


}
