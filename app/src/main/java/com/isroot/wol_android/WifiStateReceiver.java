package com.isroot.wol_android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

public class WifiStateReceiver extends BroadcastReceiver {
    public static final int WIFI_MODE_SCAN_ONLY = 2;
    public static final String WIFI_STATE_CHANGED_ACTION = "android.net.wifi.WIFI_STATE_CHANGED";
    public static final int WIFI_STATE_DISABLED = 1;
    public static final int WIFI_STATE_DISABLING = 0;
    public static final int WIFI_STATE_ENABLED = 3;
    public static final int WIFI_STATE_ENABLING = 2;
    public static final int WIFI_STATE_UNKNOWN = 4;
    public final static int NETWORK_STATE_CONNECTED = WIFI_STATE_UNKNOWN + 1;
    public final static int NETWORK_STATE_CONNECTING = NETWORK_STATE_CONNECTED + 1;
    public final static int NETWORK_STATE_DISCONNECTED = NETWORK_STATE_CONNECTING + 1;
    public final static int NETWORK_STATE_DISCONNECTING = NETWORK_STATE_DISCONNECTED + 1;
    public final static int NETWORK_STATE_SUSPENDED = NETWORK_STATE_DISCONNECTING + 1;
    public final static int NETWORK_STATE_UNKNOWN = NETWORK_STATE_SUSPENDED + 1;

    Context context;

	/*
	public interface OnChangeNetworkStatusListener
	{
		public void OnChanged(int status);
	}*/

    private WifiManager m_WifiManager = null;
    private ConnectivityManager m_ConnManager = null;
    //private OnChangeNetworkStatusListener m_OnChangeNetworkStatusListener = null;

    public WifiStateReceiver(Context context)
    {
        this.context = context;
        m_WifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        m_ConnManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

    }
/*
	public void setOnChangeNetworkStatusListener(OnChangeNetworkStatusListener listener)
	{
		m_OnChangeNetworkStatusListener = listener;
	}*/

    @Override
    public void onReceive(Context context, Intent intent)
    {

		/*
		if (m_OnChangeNetworkStatusListener == null)
		{
			return;
		}*/

        String strAction = intent.getAction();
		/*if( strAction.equals(ConnectivityManager.CONNECTIVITY_ACTION) ) {
			if( m_ConnManager.)
		}*/
        Log.d("checkWifi",""+strAction);
		/*NetworkInfo nInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
		NetworkInfo.State state= nInfo.getState();
		*/

        if (strAction.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
            switch(m_WifiManager.getWifiState()) {
                case WifiManager.WIFI_STATE_DISABLED:

                    //m_OnChangeNetworkStatusListener.OnChanged(WIFI_STATE_DISABLED);
                    break;

                case WifiManager.WIFI_STATE_DISABLING:
                    //m_OnChangeNetworkStatusListener.OnChanged(WIFI_STATE_DISABLING);
                    break;

                case WifiManager.WIFI_STATE_ENABLED:
                    //m_OnChangeNetworkStatusListener.OnChanged(WIFI_STATE_ENABLED);
                    break;

                case WifiManager.WIFI_STATE_ENABLING:
                    //m_OnChangeNetworkStatusListener.OnChanged(WIFI_STATE_ENABLING);
                    break;

                case WifiManager.WIFI_STATE_UNKNOWN:
                    //m_OnChangeNetworkStatusListener.OnChanged(WIFI_STATE_UNKNOWN);
                    break;
            }
        } else if (strAction.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
            Log.d("checkWifi","n s c a");
            NetworkInfo networkInfo = m_ConnManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            Toast t;

            if ( (networkInfo != null) && (m_WifiManager.isWifiEnabled()) ) {
                Log.d("isRoot", "wifi is enabled");
                boolean onlineFlag = checkInternet();

                if ((m_WifiManager.getConnectionInfo().getBSSID().equals("isRoot")) && onlineFlag) {
                    t = Toast.makeText(context, "Connected", Toast.LENGTH_SHORT);
                    t.show();
                    //m_OnChangeNetworkStatusListener.OnChanged(NETWORK_STATE_CONNECTED);
                }
            }
        }
    }

    private boolean checkInternet(){
        CheckConnect cc = new CheckConnect();
        cc.start();
        try {
            cc.join();
            if(cc.isSuccess()){
                return true;
            }else{
                return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

}
