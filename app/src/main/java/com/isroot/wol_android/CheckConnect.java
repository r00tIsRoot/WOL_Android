package com.isroot.wol_android;

import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;

public class CheckConnect extends Thread{
    public static final String CONNECTION_CONFIRM_CLIENT_URL = "http://clients3.google.com/generate_204";


    private boolean success;
    private String host = "http://clients3.google.com/generate_204";

//    public CheckConnect(String host){
//        this.host = host;
//    }

    @Override
    public void run() {

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection)new URL(host).openConnection();
            conn.setRequestProperty("User-Agent","Android");
            conn.setConnectTimeout(1000);
            conn.connect();
            int responseCode = conn.getResponseCode();
            Log.d("isroot", "cc response code : "+responseCode);
            if(responseCode == 204) success = true;
            else success = false;
        }
        catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        if(conn != null){
            conn.disconnect();
        }
    }

    public boolean isSuccess(){
        Log.d("isroot", "CC isSuccess : "+success);
        return success;
    }

}