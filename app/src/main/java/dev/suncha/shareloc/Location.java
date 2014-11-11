package dev.suncha.shareloc;

/**
 * Created by Sunny on 10/29/2014.
 */
public class Location {
    private int id;
    private String name;
    private String description;
    private String  address;
    private double  longitude;
    private double  latitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    // Will be used by the ArrayAdapter in the ListView
//    @Override
//    public String toString() {
//        return comment;
//    }http://www.vogella.com/tutorials/AndroidSQLite/article.html
}
