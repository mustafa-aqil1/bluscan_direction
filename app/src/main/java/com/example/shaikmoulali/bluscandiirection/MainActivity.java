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
			public int[] rssival = new int[20];
   
	public float dist=0,cn=0,ce=0,cw=0,cs=0,rssi=0;
	public float un=0,ue=0,uw=0,us=0;
  
	     public int sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0;    
    //int dist=0;
   
  public int k=0;
   


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
       // lv = (ListView) findViewById(R.id.listView);
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


   


    private final BroadcastReceiver receiver = new BroadcastReceiver() {
       
	
		 @Override
        public void onReceive(Context context, Intent intent) {

  TextView rssi_msg = (TextView) findViewById(R.id.textView3);


     CharSequence text,dir=" ";
     Context contex = getApplicationContext();
     int duration = Toast.LENGTH_SHORT;

            String action = intent.getAction();

            if (BluetoothDevice.ACTION_FOUND.equals(action)) {

                if (k < 13) {
                    if (k == 0)
                    {
                        String name = intent.getStringExtra(BluetoothDevice.EXTRA_NAME);
                        Toast.makeText(contex, name, duration).show();
                        Toast.makeText(contex, "rssi values @ 1 quadrant", duration).show();
                    }
                    else
                        if (k == 3)
                        {
                        Toast.makeText(contex, "rssi values @ 2 quadrant", duration).show();
                        }
                    else
                        if (k == 6)
                        {
                        Toast.makeText(contex, "rssi values @ 3 quadrant", duration).show();
                        }
                    else
                        if (k == 9)
                        {
                        Toast.makeText(contex, "rssi values @ 4 quadrant", duration).show();
                        }
                    else
                        if (k == 12)
                        {
                        Toast.makeText(contex, "device found", duration).show();
                   


                        for (int i = 0; i < 12; i++) {
                            if (i < 3) {
                                sum1 += rssival[i];
                            } else if (i >= 3 && i < 6) {
                                sum2 += rssival[i];
                            }
                            if (i >= 6 && i < 9) {
                                sum3 += rssival[i];
                            } else 
							if(i>=9 && i<12)
							{
                                sum4 += rssival[i];
                            }
                           // rssi_msg.setText(rssi_msg.getText() + ""+rssival[i]);
                        }

                             // rssi_msg.setText(rssi_msg.getText() + "\nsums"+sum1+" \n"+sum2+"\n"+sum3+"\n"+sum4 );

                        un =  sum1 / 3;
                        ue =  sum2 / 3;
                        uw =  sum3 / 3;
                        us =  sum4 / 3;
					 
                     //  rssi_msg.setText(rssi_msg.getText() + "\navgs"+un+"\n"+ue+"\n"+uw+"\n"+us+"\n" );


                        if (un > ue && un > uw && un > us) {
							 if (ue > uw && ue > us )
							 {
								 dir="east";
							 }
							 else
								if (us > uw && us > ue )
								{dir="south";
							 }
							 else
								 if (uw > ue && uw > us )
							 {
								 dir="south-east";
							 }
								 
								 
                            text = "Device is in 1st quadrant";
                            // Toast.makeText(contex, text, duration).show();
                            rssi_msg.setText(rssi_msg.getText() + "" + text+"\n"+dir);
                            cn++;
                        } else if (ue > un && ue > uw && ue > us) {
							
							if (un > uw && un > us )
							 {
								 dir="west";
							 }
							 else
								if (uw > un && uw > us )
								{dir="south";
							 }
							 else
								 if (us > uw && us > un )
							 {
								 dir="south-west";
							 }
                            text = "Device is in 2nd quadrant";
                            //Toast.makeText(contex, text, duration).show();
                       rssi_msg.setText(rssi_msg.getText() + "" + text+"\n"+dir);
                            ce++;
                        } else if (uw > ue && uw > un && uw > us) {
							
							if (ue > un && ue > us )
							 {
								 dir="north";
							 }
							 else
								if (us > ue && us > un )
								{dir="west";
							 }
							 else
								 if (un > ue && un > us )
							 {
								 dir="north-west";
							 }
                            text = "Device is in 3rd quadrant";
                            rssi_msg.setText(rssi_msg.getText() + "" + text);
                            //Toast.makeText(contex, text, duration).show();
                            cw++;
                        } else

                        {
							
							if (un > uw && un > ue )
							 {
								 dir="north";
							 }
							 else
								if (uw > ue && uw > un )
								{dir="east";
							 }
							 else
								 if (ue > un && ue > uw )
							 {
								 dir="north-east";
							 }
                            text = "Device is in 4th quadrant";
                              rssi_msg.setText(rssi_msg.getText() + "" + text+"\n"+dir);
                            //Toast.makeText(contex, text, duration).show();
                            cs++;
                        }

                        if (cn == 1) {
                            rssi =  un;
                        } else if (ce == 1) {
                            rssi =  ue;
                        } else if (cw == 1) {
                            rssi =  uw;
                        } else {
                            rssi = us;
                        }

                        if (rssi > -45) {
                            dist = 0;
                        } else if (-45 > rssi && rssi >= -48) {
                            dist = 1;
                        } else if (-48 > rssi && rssi >= -51) {
                            dist = 2;
                        } else if (-51 > rssi && rssi >= -54) {
                            dist = 3;
                        } else if (-54 > rssi && rssi >= -58) {
                            dist = 4;
                        } else if (-58 > rssi && rssi >= -61) {
                            dist = 5;
                        } else if (-61 > rssi && rssi >= -68) {
                            dist = 6;
                        } else if (-68 > rssi && rssi >= -71) {
                            dist = 7;
                        } else if (-71 > rssi && rssi >= -76) {
                            dist = 8;
                        } else if (-76 > rssi && rssi >= -80) {
                            dist = 9;
                        } else if (-80 > rssi) {
                            dist = 10;
                        }


                        rssi_msg.setText(rssi_msg.getText() + "\nDistance = " + dist + " metres\n");
                    }
                }


				if(k<12)
				{
                rssival[k] = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE);

                text = "value :" + rssival[k];

                Toast.makeText(contex, text, duration).show();
                k++;
				}
				


                //TextView rssi_msg = (TextView) findViewById(R.id.textView3);
                // rssi_msg.setText(rssi_msg.getText() + name + " => " + rssi + "dBm\nDistance =" + dist +" metres\n");  //to print the rssi value.



            }
        }
    };


			


	
}

