

package com.example.perfe.myapplication3;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

import android.net.wifi.p2p.WifiP2pManager.Channel;


//import it.polimi.deib.p2pchat.discovery.model.LocalP2PDevice;

/**
 * Created by perfe on 1/09/2017.
 */



public class WiFiDirectBroadcastReceiver extends BroadcastReceiver {

    private final WifiP2pManager mManager;
    private final WifiP2pManager.Channel channel;
    public final Activity activity;

    public WiFiDirectBroadcastReceiver(WifiP2pManager mManager, WifiP2pManager.Channel mChannel, MainActivity mainActivity) {
        super();
        this.mManager= mManager;
        this.channel = mChannel;
        this.activity = mainActivity;


    }



    //  @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                // Wifi P2P is enabled
                System.out.println("Connection Establish");
            } else {
                // Wi-Fi P2P is not enabled
                System.out.println("Connection Abort");
                System.exit(0);
            }
        }
    }
}


