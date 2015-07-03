package com.example.pennryan.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CustomListViewActivity extends Activity {

    @Bind(R.id.tv)
    TextView tv;

    @Bind(R.id.lv)
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);
        ButterKnife.bind(this);

        ActionBar bar = getActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        MyAdatper adatper = new MyAdatper(this);
        lv.setAdapter(adatper);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom_list_view, menu);
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

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<HashMap<String, Object>> getDate() {

        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 30; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle", "第" + i + "行");
            map.put("ItemText", "这是第" + i + "行");
            listItem.add(map);
        }
        return listItem;
    }


    class MyAdatper extends BaseAdapter {

        LayoutInflater inflater;

        public MyAdatper(Context context){
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return getDate().size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            Log.v("ListView", "getView " + i + " " + view);

            ViewHolder holder;
            if (view == null) {

                view = this.inflater.inflate(R.layout.custom_list_item, null);

                holder = new ViewHolder();
                holder.button = (Button) view.findViewById(R.id.ItemButton);
                holder.title = (TextView) view.findViewById(R.id.ItemTitle);
                holder.text = (TextView) view.findViewById(R.id.ItemText);

                view.setTag(holder);

            } else {

                holder = (ViewHolder) view.getTag();
            }

            holder.title.setText(getDate().get(i).get("ItemTitle").toString());
            holder.text.setText(getDate().get(i).get("ItemText").toString());

            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv.setText("你点击了第 " + i + " 项");
                }
            });

            return view;
        }
    }

    class ViewHolder{
        public Button button;
        public TextView title;
        public TextView text;
    }

}
