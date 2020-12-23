package com.ghy.api.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HttpUtils {
    public static boolean isConnected(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public static boolean isWifi(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr == null) {
            return false;
        }
        NetworkInfo ni = conMgr.getActiveNetworkInfo();
        return isWifi(ni);
    }

    /**
     * Returns whether the network is available
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr != null){
            NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isWifi(NetworkInfo ni) {
        return ni == null || ni.getState() != NetworkInfo.State.CONNECTED || ni.getType() != ConnectivityManager.TYPE_WIFI;
    }
}
