package dev.suncha.shareloc;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class saveLocationDetails extends Activity {
    Button saveButton,discardButton;
    double longitude,latitude;
    String address="";
    EditText etAddress,etDescription;
    TextView tvLongitude,tvLatitiude;
    private MySQLiteHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_location_details);

        mydb = new MySQLiteHelper(this);

        saveButton = (Button)findViewById(R.id.saveButton);
        discardButton=(Button)findViewById(R.id.discardButton);

        etAddress=(EditText)findViewById(R.id.etAddress);
        etDescription=(EditText)findViewById(R.id.etDescription);
        tvLatitiude=(TextView)findViewById(R.id.tvlatitude);
        tvLongitude=(TextView)findViewById(R.id.tvlongitude);

        Bundle extras =getIntent().getExtras();
        longitude=extras.getDouble("extralongitude");
        latitude=extras.getDouble("extralatitude");
        address=extras.getString("address");

        tvLongitude.setText(Double.toString(longitude));
        tvLatitiude.setText(Double.toString(latitude));


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DO THINGS TO WRITE THE INFORMATION TO DATABASE
                if(etAddress.getText().toString().trim().length()==0||etDescription.getText().toString().trim().length()==0){
                    Toast.makeText(getApplicationContext(),R.string.incomplete_info,Toast.LENGTH_SHORT).show();
                }else{
                    mydb.insertLocation(etAddress.getText().toString(),etDescription.getText().toString(),etAddress.getText().toString(),tvLongitude.getText().toString(),tvLatitiude.getText().toString());
                    Log.d("Insert","Inserting "+etAddress.getText().toString()+" with longitude "+tvLongitude.getText().toString());
                    Toast.makeText(getApplicationContext(),R.string.success,Toast.LENGTH_SHORT).show();
                }

            }
        });

        discardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //JUST GO BACK TO THE PREVIOUS ACTIVITY (SAMPLEACTIVITY.JAVA)
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save_location_details, menu);
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
}
