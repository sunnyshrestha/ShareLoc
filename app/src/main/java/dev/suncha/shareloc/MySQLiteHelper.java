package dev.suncha.shareloc;

/**
 * Created by Sunny on 10/29/2014.
 * http://www.vogella.com/tutorials/AndroidSQLite/article.html
 * http://www.tutorialspoint.com/android/android_sqlite_database.htm
 * http://hmkcode.com/android-simple-sqlite-database-tutorial/
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_LOCATIONS="locations";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_NAME_OF_PLACE="name";
    public static final String DESCRIPTION="description";
    public static final String ADDRESS = "address";
    public static final String LONGITUDE = "longitude";
    public static final String LATITUDE= "latitude";

    //private HashMap hp;
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_LOCATIONS + "("+COLUMN_ID
            +" INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN_NAME_OF_PLACE
            +" TEXT,"+ DESCRIPTION +" TEXT,"
            +ADDRESS+" TEXT,"
            +LONGITUDE+" TEXT,"
            +LATITUDE+" TEXT" + ")";
    private static final String DATABASE_NAME = "locations.db";
    private static final int DATABASE_VERSION = 1;


    public MySQLiteHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + i + " to "
                        + i2 + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);
        onCreate(sqLiteDatabase);
    }

    //CRUD Operations

    //Add one location

    public boolean insertLocation(Location addLocation) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME_OF_PLACE, addLocation.getName());
        contentValues.put(DESCRIPTION, addLocation.getDescription());
        contentValues.put(ADDRESS, addLocation.getAddress());
        contentValues.put(LONGITUDE, addLocation.getLongitude());
        contentValues.put(LATITUDE, addLocation.getLatitude());

        sqLiteDatabase.insert(TABLE_LOCATIONS, null, contentValues);
        sqLiteDatabase.close();
        return true;
    }

    //Getting single location
    Location getLocation(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_LOCATIONS, new String[]{COLUMN_ID,
                        DESCRIPTION, ADDRESS, LONGITUDE, LATITUDE}, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Location location = new Location(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return location;
    }

    public Cursor getData (int id){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from locations where id="+id+"",null);
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(sqLiteDatabase,TABLE_LOCATIONS);
        return numRows;
    }

    public boolean updateLocation (Integer id, String name, String description, String address, String longitude, String latitude){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COLUMN_NAME_OF_PLACE,name);
        contentValues.put(DESCRIPTION,description);
        contentValues.put(ADDRESS,address);
        contentValues.put(LONGITUDE,longitude); //remove these as user cannot change lat and long
        contentValues.put(LATITUDE,latitude);
        sqLiteDatabase.update(TABLE_LOCATIONS,contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteLocation (Integer id){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_LOCATIONS,"id = ? ", new String[] {Integer.toString(id)});
    }

    public ArrayList getAllLocations(){
        ArrayList array_list = new ArrayList();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from locations",null);
        res.moveToFirst();
        while (res.isAfterLast()==false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_NAME_OF_PLACE)));
            res.moveToNext();
        }
        return array_list;
    }

    public Cursor SelectData() {
        // TODO Auto-generated method stub

        try {

            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data

            Cursor cursor = db.query(TABLE_LOCATIONS, new String[]{"ID AS _id, *"}, null, null, null, null, null);

            if (cursor != null) {
                return cursor;
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }

    }

    //http://www.tutorialspoint.com/android/android_sqlite_database.htm
    //http://dj-android.blogspot.in/2012/10/android-show-data-from-sqlite-db-into.html
}
