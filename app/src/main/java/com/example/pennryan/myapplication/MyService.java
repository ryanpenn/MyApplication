package com.example.pennryan.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    static int step = 0;

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
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LifeCycle", ++step + " Service.onStartCommand()");
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
