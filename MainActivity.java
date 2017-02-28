package com.example.shaikmoulali.bluscandiirection;

import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.hardware.SensorManager;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import android.widget.Toast;
import java.util.ArrayList;
import java.util.Set;

import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;




import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.hardware.SensorManager;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import android.widget.Toast;
import java.util.ArrayList;
import java.util.Set;

import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;




public class MainActivity extends Activity {
    Button b1, b2, b3, b4, b5;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    ListView lv;
    int[] rssival = new int[12];
    int[] rssin = new int[3];
    int[] rssie = new int[3];
    int[] rssiw = new int[3];
    int[] rssis = new int[3];
    float un;
    float ue;
    float uw;
    float us;
    int sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0;
    CharSequence text;
    float sd1 = 0;
    float sd2 = 0;
    int i = 0;
    float sd3 = 0;
    float sd4 = 0;
    Context contex = getApplicationContext();
    int duration = Toast.LENGTH_SHORT;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerReceiver(receiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));

        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b4 = (Button) findViewById(R.id.button4);
        b5 = (Button) findViewById(R.id.button5);

        BA = BluetoothAdapter.getDefaultAdapter();
        lv = (ListView) findViewById(R.id.listView);
        b3 = (Button) findViewById(R.id.button3);
        b3.setOnClickListener(new OnClickListener() {
                                  public void onClick(View v) {
                                      BA.startDiscovery();
                                  }
                              }
        );
    }

    public void on(View v) {
        if (!BA.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(getApplicationContext(), "Turned on", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Already on", Toast.LENGTH_LONG).show();
        }
    }

    public void off(View v) {
        BA.disable();
        Toast.makeText(getApplicationContext(), "Turned off", Toast.LENGTH_LONG).show();
    }


    public void visible(View v) {
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }


    /* public void list(View v) {
       pairedDevices = BA.getBondedDevices();

        ArrayList list = new ArrayList();

        for(BluetoothDevice bt : pairedDevices) list.add(bt.getName());
        Toast.makeText(getApplicationContext(), "Showing Paired Devices",Toast.LENGTH_SHORT).show();

        final ArrayAdapter adapter = new  ArrayAdapter(this,android.R.layout.simple_list_item_1, list);

        lv.setAdapter(adapter);*/


    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int dist = 0;

            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //           String name = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);

                //		for(i=0;i<12;i++)

                if (i == 0) {
                    text = "rssi values @ 1 quadrant";
                } else if (i == 3) {
                    text = "rssi values @ 2 quadrant";
                } else if (i == 6) {
                    text = "rssi values @ 3 quadrant";
                } else if (i == 9) {
                    text = "rssi values @ 4 quadrant";
                } else if (i == 12) {
                    text = "rssi for all directions taken";
                }

                Toast.makeText(contex, text, duration).show();


                rssival[i] = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE);

                text = "value : " + rssival[i];
                i++;
                Toast.makeText(contex, text, duration).show();
            }

        }
    };

}
/*
    public void output(View v) {


            for (int i = 0,j=0; i < 5; i++,j++)
                rssin[j] =rssival[i];

            for (int i = 5,j=0; i < 10; i++,j++)
                rssie[j] = rssival[i];

            for (int i = 10,j=0; i < 15; i++,j++)
                rssiw[j]=rssival[i];

            for (int i = 15,j=0; i < 20; i++,j++)
                rssis[j]=rssival[i];




            for (int i = 0; i < 5; i++) {
                sum1 += rssin[i];
                sum2 += rssie[i];
                sum3 += rssiw[i];
                sum4 += rssis[i];
            }
            un = -1 * sum1 / 5;
            ue = -1 * sum2 / 5;
            uw = -1 * sum3 / 5;
            us = -1 * sum4 / 5;

            for (int i = 0; i < 5; i++) {
                sd1 += (float) Math.pow(rssin[i] - un, 2);
                sd2 += (float) Math.pow(rssie[i] - ue, 2);
                sd3 += (float) Math.pow(rssiw[i] - uw, 2);
                sd4 += (float) Math.pow(rssis[i] - us, 2);
            }
            sd1 = (float) Math.sqrt(sd1 / 5);
            sd2 = (float) Math.sqrt(sd2 / 5);
            sd3 = (float) Math.sqrt(sd3 / 5);
            sd4 = (float) Math.sqrt(sd4 / 5);

            if (un > ue && un > uw && un > us) {
                text = "device is in north direction";
                Toast.makeText(contex, text, duration).show();
            } else if (ue > un && ue > uw && ue > us) {
                text = "device is in east direction";
                Toast.makeText(contex, text, duration).show();
            } else if (uw > ue && uw > un && uw > us) {
                text = "device is in west direction";
                Toast.makeText(contex, text, duration).show();
            } else

            {
                text = "device is in south direction";
                Toast.makeText(contex, text, duration).show();
            }

        }
*/
//    public void BroadcastReceiver(View view) {
 //   }

    //close Range
               /* float meansd;
                meansd=(sd1+sd2+sd3+sd4)/4;
                if(meansd<2) {
                    text = "DEVICE IS IN CLOSE RANGE";
                    Toast.makeText(contex, text, duration).show();
                } */


             /* public  class rssit
                {int rssival[5];

				int returnrssi()
				{

						for (int i = 0; i < 5; i++)
                        rssival[i] = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE);
						for(i=0;i<5;i++)
					{

						return rssival[i];
				}
				}
				} */


               /* if(rssi>-45) {
                    dist=0;
                }
                else
                if(-45>rssi && rssi>=-48)
                    dist=1;
                else
                if(-48>rssi && rssi>=-51)
                    dist=2;
                else
                if(-51>rssi && rssi>=-54)
                    dist=3;
                else
                if(-54>rssi && rssi>=-58)
                    dist=4;
                else
                if(-58>rssi && rssi>=-61)
                    dist=5;
                else
                if(-61>rssi && rssi>=-68)
                    dist=6;
                else
                if(-68>rssi && rssi>=-71)
                    dist=7;
                else
                if(-71>rssi && rssi>=-76)
                    dist=8;
                else
                if(-76>rssi && rssi>=-80)
                    dist=9;
                else
                if(-80>rssi)
                    dist=10;*/


        //  TextView rssi_msg = (TextView) findViewById(R.id.textView3);
        //rssi_msg.setText(rssi_msg.getText() + name + " => " + rssi + "dBm\nDistance =" + dist +" metres\n");  //to print the rssi value.








