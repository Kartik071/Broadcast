package com.example.broadcastrec;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.util.Log;

public class airplaneRec extends BroadcastReceiver {
    onAirplaneMode mode;
    String TAG="Android:";
    public airplaneRec(onAirplaneMode mode) {
        Log.d(TAG, "airplaneRec: ");
        this.mode = mode;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: ");
        if(Settings.System.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) != 0) {
           mode.onAirplaneMode(true);
        } else {
            mode.onAirplaneMode(false);
    }
        //final String action=intent.getAction();
//        if (Settings.System.getInt(context.getContentResolver(),Settings.Global.AIRPLANE_MODE_ON,0)==0){
//            Log.d(TAG, "onReceive: ");
//            mode.onAirplaneMode(false);
//        }
//        else
//            mode.onAirplaneMode(true);
//    }

    }

}

