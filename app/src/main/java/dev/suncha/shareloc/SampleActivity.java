package dev.suncha.shareloc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class SampleActivity extends Activity {
    TextView tv1, tv2,tv3;
    LocationManager lm;
    LocationListener ll;
    Button mapButton,save;
    double longitudeformap = 0, latitudeformap = 0;
    ProgressDialog progress;
    Intent refresh,saveLocationDetailsIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3= (TextView) findViewById(R.id.tv3);

        mapButton = (Button) findViewById(R.id.mapbutton);
        save=(Button)findViewById(R.id.save_location);
        refresh = new Intent(this, SampleActivity.class);
        ll = new myLocationListener();

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        progress = new ProgressDialog(this);
        progress.setTitle(R.string.progress_title);
        progress.setMessage("Move to an area with open sky for faster GPS fix.");
        progress.setIndeterminate(true);
        progress.setCancelable(false);

        getlocation();

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (latitudeformap != 0 && latitudeformap != 0) {
                    double lat = latitudeformap;
                    double lon = longitudeformap;
                    String uri = "geo:" + lat + "," + lon;
                    startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));
                } else {
                    Toast.makeText(getApplicationContext(), R.string.locationnotfixed, Toast.LENGTH_SHORT).show();
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveLocationDetailsIntent = new Intent(getApplicationContext(),saveLocationDetails.class);
                saveLocationDetailsIntent.putExtra("extralongitude",longitudeformap);
                saveLocationDetailsIntent.putExtra("extralatitude",latitudeformap);
                startActivity(saveLocationDetailsIntent);

            }
        });
    }

    private void getlocation() {
        //IF GPS IS NOT ENABLED, CALL AN ALERT DIALOG
        //ELSE SHOW PROGRESS DIALOG AND REQUEST LOCATION UPDATES
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }else{
            progress.show();
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
        }

    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.alert_msg)
                .setTitle(R.string.alert_dialog_title)
                .setCancelable(false)
                .setPositiveButton(R.string.enable, new DialogInterface.OnClickListener() {
                    public void onClick(
                            @SuppressWarnings("unused")
                            final DialogInterface dialog,
                            @SuppressWarnings("unused")
                            final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                        tv1.setText(R.string.cannot_get);
                        tv2.setText(R.string.cannot_get);
                        //tv3.setText(R.string.cannot_get);
                        if (progress.isShowing()) {
                            progress.dismiss();
                        }
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


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
        if(id==R.id.shareicon){
            if(latitudeformap!=0&&longitudeformap!=0) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey, you can find me here: www.google.com/maps?q=" + latitudeformap + "," +
                        longitudeformap);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent,getResources().getText(R.string.share)));
            }else{
                Toast.makeText(getApplicationContext(), R.string.locationnotfixed, Toast.LENGTH_SHORT).show();
            }
        }
        if (id == R.id.refresh) {
            startActivity(refresh);//Start the same Activity
            finish(); //finish Activity.
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        //lm.removeUpdates(ll);
        super.onPause();
    }

    @Override
    protected void onResume(){
        //CHECK IF THE USER HAS ENABLED GPS
        //IF YES, POPUP THE PROGRESS BAR AND GET LOCATION UPDATE
        //ELSE, POP UP ALERT DIALOG TO ENABLE GPS
        if(lm.isProviderEnabled(LocationManager.GPS_PROVIDER)){
           //progress=ProgressDialog.show(this,"Getting co-ordinates","Move to an area with visible sky for a faster GPS fix.",true);
            progress.show();
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
            if(tv1.getText().toString().length()>0){
                progress.dismiss();
            }
        }else{
            //buildAlertMessageNoGps();
            Toast.makeText(this,"Back",Toast.LENGTH_SHORT).show();
        }
        super.onResume();
    }

    private class myLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {

            if (location != null) {
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();

                longitudeformap = longitude;
                latitudeformap = latitude;

                tv1.setText(Double.toString(longitude));
                tv2.setText(Double.toString(latitude));
                lm.removeUpdates(ll);
                lm = null;
                if(progress.isShowing()){
                    progress.dismiss();
                }
            }
        }
/*
        public boolean isOnline() {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                return true;
            }
            return false;
        }*/

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

}
