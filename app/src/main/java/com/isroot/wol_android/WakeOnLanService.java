package com.isroot.wol_android;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class WakeOnLanService extends Service {
    private BroadcastReceiver mReceiver;

    public WakeOnLanService(BroadcastReceiver mReceiver) {
        this.mReceiver = mReceiver;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("isroot", "WakeOnLanService onCreate");
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        mReceiver = new WifiStateReceiver(this);
        registerReceiver(mReceiver, filter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.d("isroot", "WakeOnLanService onStartCommand");
        if( intent == null)
        {
            IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            mReceiver = new WifiStateReceiver(this);
            registerReceiver(mReceiver, filter);
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("isroot", "WakeOnLanService onDestroy");
        unregisterReceiver(mReceiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
