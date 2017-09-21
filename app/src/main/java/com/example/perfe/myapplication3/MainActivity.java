package com.example.perfe.myapplication3;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import static android.R.attr.action;

public class MainActivity extends AppCompatActivity {
    WifiP2pManager mManager;
    WifiP2pManager.Channel mChannel;
    BroadcastReceiver mReceiver;
    private IntentFilter mIntentFilter = new IntentFilter();
    public static final String Tag = "YOUR-TAG-NAME";

    private View container;
     private View hello;
     private View Profile;

     private boolean playAnimations = true;


    /*
    Discovering Peer
     */
    public void discover(){
        mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {

                Toast.makeText(MainActivity.this, "Discovery Initiated",

                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int reasonCode) {
                Toast.makeText(MainActivity.this, "Discovery Failed : " + reasonCode,

                        Toast.LENGTH_SHORT).show();
            }
        });

        WifiP2pManager.PeerListListener myPeerListListener = null;

        if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {

            // request available peers from the wifi p2p manager. This is an
            // asynchronous call and the calling activity is notified with a
            // callback on PeerListListener.onPeersAvailable()
            if (mManager != null) {
                mManager.requestPeers(mChannel, myPeerListListener);
            }

        }}

    //obtain a peer from the WifiP2pDeviceList
    WifiP2pDevice device;
    WifiP2pConfig config = new WifiP2pConfig();
    public void onPeersAvailable(WifiP2pDeviceList wifiP2pDeviceList) {

        for (WifiP2pDevice device : wifiP2pDeviceList.getDeviceList())
        {
            if (device.deviceName.equals("ABC")) Log.d(Tag,"found device!!! ");
            // device.deviceName
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(this, getMainLooper(), null);
        mReceiver = new WiFiDirectBroadcastReceiver(mManager, mChannel, this);
        setContentView(R.layout.activity_main);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
    }
    /* unregister the broadcast receiver */
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }



    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.cointainer);
        hello = findViewById(R.id.welcome);
        Profile = findViewById(R.id.profile);
         }


         @Override
         public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(hasFocus && playAnimations){
             showContainer();
             showOtherItems();
             playAnimations = false;
        }
         }

        private void showContainer() {

         container.animate().alpha(1f).setDuration(1000);


         }


         private void showOtherItems() {

         float startXhello = 0 - hello.getWidth();

         float endXhello = hello.getX();

         ObjectAnimator Animhello = ObjectAnimator.ofFloat(hello, View.X, startXhello, endXhello);

         Animhello.setDuration(1500);
         hello.setVisibility(View.VISIBLE);
         Animhello.start();

         PropertyValuesHolder scaleXHolder = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f);

         PropertyValuesHolder scaleYHolder = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f);

         ObjectAnimator AnimProfile = ObjectAnimator.ofPropertyValuesHolder(Profile, scaleXHolder, scaleYHolder);

         AnimProfile.setDuration(1500);

         AnimProfile.start();


           }

        }





