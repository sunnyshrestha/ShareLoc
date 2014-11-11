package dev.suncha.shareloc;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import android.location.Location;
//import dev.suncha.shareloc.FallbackLocationTracker;
//import android.widget.Toast;
//import dev.suncha.shareloc.GPSTracker;



public class MyActivity extends Activity implements LocationListener {
    LocationManager myLocationManager;
    String provider;
    Location l;
    TextView tv1,tv2;
    Button getLocation;
    //private GPSTracker.FirstFixListener firstFixListener;
    //private GPSTracker.LocationUpdateListener locationUpdateListener;
    //FallbackLocationTracker fobj;
    double current_latitude,current_longitude;
    //private GPSTracker gps;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        tv1 = (TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv2);
        //getLocation=(Button)findViewById(R.id.button1);
        myLocationManager=(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);




       /* getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*fobj=new FallbackLocationTracker(MyActivity.this);
                fobj.start();

                if(fobj.hasLocation())
                {
                    Location locobj=fobj.getLocation();
                    current_latitude=locobj.getLatitude();
                    current_longitude=locobj.getLongitude();
                    tvLongitude.setText(Double.toString(current_longitude));
                    tvLatitude.setText(Double.toString(current_latitude));
                   // Toast.makeText(getApplicationContext(),current_latitude+" "+current_longitude,Toast.LENGTH_LONG).show();
                    //placemarkersonmap();
                }*//*

                Criteria c=new Criteria();
                //criteria object will select best service based on
                //Accuracy, power consumption, response, bearing and monetary cost
                //set false to use best service otherwise it will select the default Sim network
                //and give the location based on sim network
                //now it will first check satellite than Internet than Sim network location
                provider=myLocationManager.getBestProvider(c,false);
                //now you have best provider
                //get location
                l=myLocationManager.getLastKnownLocation(provider);
                if(l!=null){
                    double extralongitude = l.getLongitude();
                    double extralatitude = l.getLatitude();
                    tv1.setText(Double.toString(extralongitude));
                    tv2.setText(Double.toString(extralatitude));
                }else{
                    tv1.setText("No Provider");
                    tv2.setText("No Provider");
                }


            }
        });*/

    }

    /*private void sendGPStoSMS() {
        gps = GPSTracker.getInstance(context);
        firstFixListener = new MyFirstFixListener();
        locationUpdateListener = new MyLocationUpdateListener();
        gps.startUsingGPS(firstFixListener, locationUpdateListener);

    }

    private class MyFirstFixListener implements GPSTracker.FirstFixListener {

        @Override
        public void onFirsFixChanged(boolean hasGPSfix) {
            if (hasGPSfix == true) {
                Location position = gps.getLocation();
                // send SMS with position

                // stop the gps and unregister callbacks
                gps.stopUsingGPS(firstFixListener, locationUpdateListener);
            }

        }

    }*/

  /*  private class MyLocationUpdateListener implements GPSTracker.LocationUpdateListener {

        @Override
        public void onLocationChanged(Location location) {
            // hand you each new location from the GPS
            // you do not need this as you only want to send a single position

        }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {
        double longitude = l.getLongitude();
        double latitude = l.getLatitude();
        tv1.setText(Double.toString(longitude));
        tv2.setText(Double.toString(latitude));


    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
