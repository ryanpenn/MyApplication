package com.example.pennryan.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.SearchView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.example.pennryan.myapplication.ActionBar.AlbumFragment;
import com.example.pennryan.myapplication.ActionBar.ArtistFragment;
import com.example.pennryan.myapplication.ActionBar.TabListener;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ActionBarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);

        setTitle("Action bar");
        ActionBar bar = getActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        setOverflowShowingAlways();

        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.Tab tab = bar.newTab()
                .setText("artist")
                .setTabListener(new TabListener<ArtistFragment>(this, "artist", ArtistFragment.class));
        bar.addTab(tab);

        tab = bar.newTab()
                .setText("album")
                .setTabListener(new TabListener<AlbumFragment>(this, "album", AlbumFragment.class));
        bar.addTab(tab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                Toast.makeText(ActionBarActivity.this, "Expand", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                Toast.makeText(ActionBarActivity.this, "Collapse", Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ActionBarActivity.this, searchView.getQuery(), Toast.LENGTH_SHORT).show();
            }
        });

        MenuItem shareItem = menu.findItem(R.id.action_share);
        ShareActionProvider provider = (ShareActionProvider)shareItem.getActionProvider();
        provider.setShareIntent(getDefaultIntent());

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                //finish();
                Intent intent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, intent)) {
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(intent)
                            .startActivities();
                } else {
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    NavUtils.navigateUpTo(this, intent);
                }
                return true;
            case R.id.action_compose:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        return intent;
    }
}
