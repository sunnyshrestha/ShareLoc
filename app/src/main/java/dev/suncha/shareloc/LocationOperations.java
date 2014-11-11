package dev.suncha.shareloc;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by Sunny on 11/4/2014.
 */
public class LocationOperations {

    private MySQLiteHelper mySQLiteHelper;
    private  String [] LOCATION_TABLE_COLUMNS={MySQLiteHelper.COLUMN_ID,MySQLiteHelper.COLUMN_NAME_OF_PLACE,MySQLiteHelper.DESCRIPTION,MySQLiteHelper.LONGITUDE,MySQLiteHelper.LATITUDE};
    private SQLiteDatabase database;

    public LocationOperations(Context context){
        mySQLiteHelper= new MySQLiteHelper(context);
    }

    public void open() throws SQLException{
        database=mySQLiteHelper.getWritableDatabase();
    }

    public void close(){
        mySQLiteHelper.close();
    }

//    public Location addLocation(String name){
//        ContentValues values = new ContentValues();
//        values.put(MySQLiteHelper.COLUMN_NAME_OF_PLACE,name);
//
//
//    }
}
