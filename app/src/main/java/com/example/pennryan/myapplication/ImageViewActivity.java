package com.example.pennryan.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ImageViewActivity extends Activity {

    @Bind(R.id.btn1)
    Button btn1;

    @Bind(R.id.btn2)
    Button btn2;

    @Bind(R.id.btn3)
    Button btn3;

    @Bind(R.id.iv)
    ImageView iv;

    @Bind(R.id.network_image_view)
    NetworkImageView network_image_view;

    RequestQueue rQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        ButterKnife.bind(this);

        rQueue = Volley.newRequestQueue(this);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestImage("http://static.cnbetacdn.com/article/2015/0703/4333a673e8227d5.jpg");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImage("http://static.cnbetacdn.com/article/2015/0703/5b3c165ab51de15.jpg");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                universalLoadImage("http://static.cnbetacdn.com/article/2015/0703/01babff2a93e494.jpg");
            }
        });

        ImageLoader loader = new ImageLoader(rQueue, new BitmapCache());
        network_image_view.setDefaultImageResId(R.drawable.ic_launcher);
        network_image_view.setErrorImageResId(R.drawable.ic_launcher);
        network_image_view.setImageUrl("http://static.cnbetacdn.com/topics/12-08-24%2023-32-07.gif", loader);
    }

    void requestImage(String url) {

        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                iv.setImageBitmap(response);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iv.setImageResource(R.drawable.ic_launcher);
            }
        });

        rQueue.add(request);
    }

    void loadImage(String url) {

        ImageLoader loader = new ImageLoader(rQueue, new BitmapCache());

        ImageLoader.ImageListener listener = ImageLoader.getImageListener(iv,
                R.drawable.ic_launcher, R.drawable.ic_launcher);

        loader.get(url, listener, 200, 200);
    }

    void universalLoadImage(String url) {

        com.nostra13.universalimageloader.core.ImageLoader loader
                = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
        loader.init(ImageLoaderConfiguration.createDefault(ImageViewActivity.this));
        loader.displayImage(url, iv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_view, menu);
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

    public class BitmapCache implements ImageLoader.ImageCache {

        LruCache<String, Bitmap> mCache;

        public BitmapCache() {
            int maxSize = 10 * 1024 * 1024;
            mCache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return mCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCache.put(url, bitmap);
        }
    }
}
