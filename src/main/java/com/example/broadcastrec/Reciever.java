package com.example.broadcastrec;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.provider.Settings;

public class Reciever extends BroadcastReceiver {
    onConnectivityChange listener;
    onAirplaneMode listener2;

    public Reciever(onConnectivityChange boolChange) {
        this.listener = boolChange;
    }
//    public Reciever(onAirplaneMode boolchange1) {
//
//    }


    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            boolean NoConnectivity = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
            );
            if (NoConnectivity) {
                listener.onConnectionChange(false);

            } else
                listener.onConnectionChange(true);


        }
    }
}


