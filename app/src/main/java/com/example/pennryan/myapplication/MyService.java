package com.example.pennryan.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyService extends Service {

    static int step = 0;

    RequestQueue rQueue;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        Log.i("LifeCycle", ++step + " Service.onBind()");
        return null;
    }

    @Override
    public void onCreate() {
        Log.i("LifeCycle", ++step + " Service.onCreate()");
        rQueue = Volley.newRequestQueue(MyService.this);

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LifeCycle", ++step + " Service.onStartCommand()");

        new Thread(new Runnable() {
            @Override
            public void run() {

                StringRequest request = new StringRequest(Request.Method.GET, "http://www.baidu.com",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.i("Http", response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Http", error.getMessage(), error);
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("wd", "abc");
                        return map;
                    }
                };

                rQueue.add(request);

                JsonObjectRequest jRequest = new JsonObjectRequest("http://121.40.146.19:80/hello", null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("Http", response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Http", error.getMessage(), error);
                        }
                    }
                );

                rQueue.add(jRequest);

        }}).start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("LifeCycle", ++step + " Service.onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i("LifeCycle", ++step + " Service.onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i("LifeCycle", ++step + " Service.onRebind()");
        super.onRebind(intent);
    }

}
