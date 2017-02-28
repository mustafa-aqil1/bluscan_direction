package com.example.shaikmoulali.bluscandiirection;

import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
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
    Button b1, b2, b3, b4;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
      ListView lv;
    
    //int dist=0;
   
  public int k=0;
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
        //b5 = (Button) findViewById(R.id.button5);

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

        public void on(View v){
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
			 int[] rssival = new int[12];
    /* int[] rssi1 = new int[3];
     int[] rssi2 = new int[3];
     int[] rssi3 = new int[3];
     int[] rssi4 = new int[3];
	*/
	float un= 0;
     float ue=0;
     float uw=0;
     float us=0;
  
     int sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0;
     CharSequence text;
     Context contex = getApplicationContext();
     int duration = Toast.LENGTH_SHORT;

            String action = intent.getAction();
            //if conditions for printing the distance.
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {

                 if (k == 0) {
                     String name = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
                     Toast.makeText(contex, name, duration).show();
                     Toast.makeText(contex, "rssi values @ 1 quadrant", duration).show();
                } else if (k == 3) {
                     Toast.makeText(contex, "rssi values @ 2 quadrant", duration).show();
                } else if (k == 6) {
                     Toast.makeText(contex, "rssi values @ 3 quadrant", duration).show();
                } else if (k == 9) {
                     Toast.makeText(contex, "rssi values @ 4 quadrant", duration).show();
                } else if (k == 12)
					{
                     Toast.makeText(contex, "device found", duration).show();
                     /*for (int i = 0,j=0; i < 3; i++,j++) {
                         rssi1[j] = rssival[i];
                     }
                     for (int i = 3,j=0; i < 6; i++,j++) {
                         rssi2[j] = rssival[i];
                     }
                     for (int i = 6,j=0; i < 9; i++,j++) {
                         rssi3[j] = rssival[i];
                     }
                     for (int i = 9,j=0; i < 12; i++,j++) {
                         rssi4[j] = rssival[i];
                     }
                     */



                     for (int i = 0; i <12; i++) {
                         if (i < 3) {
                             sum1 += rssival[i];
                         } else if (i >= 3 && i < 6) {
                             sum2 += rssival[i];
                         }
                         if (i >= 6 && i < 9) {
                             sum3 += rssival[i];
                         } else {
                             sum4 += rssival[i];
                         }
                     }
                     un = -1 * sum1/3;
                     ue = -1 * sum2/3;
                     uw = -1 * sum3/3;
                     us = -1 * sum4/3;
/*
            for (int i = 0; i < 5; i++) {
                sd1 += (float) Math.pow(rssi1[i] - un, 2);
                sd2 += (float) Math.pow(rssi2[i] - ue, 2);
                sd3 += (float) Math.pow(rssi3[i] - uw, 2);
                sd4 += (float) Math.pow(rssi4[i] - us, 2);
            }
            sd1 = (float) Math.sqrt(sd1 / 5);
            sd2 = (float) Math.sqrt(sd2 / 5);
            sd3 = (float) Math.sqrt(sd3 / 5);
            sd4 = (float) Math.sqrt(sd4 / 5);
*/
                     if (un > ue && un > uw && un > us) {
                         text = "device is in 1st quadrant";
                         Toast.makeText(contex, text, duration).show();
                     } else if (ue > un && ue > uw && ue > us) {
                         text = "device is in 2nd quadrant";
                         Toast.makeText(contex, text, duration).show();
                     } else if (uw > ue && uw > un && uw > us) {
                         text = "device is in 3rd quadrant";
                         Toast.makeText(contex, text, duration).show();
                     } else

                     {
                         text = "device is in 4th quadrant";
                         Toast.makeText(contex, text, duration).show();
                     }

                 }




                rssival[k] = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE);

                text = "value : " + rssival[k];
                k++;
                Toast.makeText(contex, text, duration).show();




                //TextView rssi_msg = (TextView) findViewById(R.id.textView3);
               // rssi_msg.setText(rssi_msg.getText() + name + " => " + rssi + "dBm\nDistance =" + dist +" metres\n");  //to print the rssi value.






            }
        }
    };


			//public void output(View v) {}


	
}

