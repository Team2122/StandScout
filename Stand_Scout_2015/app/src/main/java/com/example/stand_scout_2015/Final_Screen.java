package com.example.stand_scout_2015;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.File;

/**
 * Created by Daryl on 2/23/2015.
 */
public class Final_Screen extends Activity {

    Button bSynced,bNoSync;
    Intent auto;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_screen);

        bSynced = (Button) findViewById(R.id.bSynced);
        bNoSync = (Button) findViewById(R.id.bNoSync);
        auto = new Intent(this,Autonomous.class);

        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(true);

        View.OnClickListener listen = new View.OnClickListener(){

            @Override
            public void onClick(View v){
                switch (v.getId()){
                    case R.id.bSynced: Global.f = new File(Environment.getExternalStorageDirectory() + "/" + Global.robot + ".csv");
                    case R.id.bNoSync: WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                                       wifi.setWifiEnabled(false);
                                       startActivity(auto);
                }
            }
        };
        bSynced.setOnClickListener(listen);
        bNoSync.setOnClickListener(listen);
    }
}
