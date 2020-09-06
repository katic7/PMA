package ftn.tim34.weplay.model;

import com.google.gson.annotations.SerializedName;

public class GamingRoom {

    @SerializedName("id")
    private Long id;

    @SerializedName("name")
    private String name;

    @SerializedName("rating")
    private float rating;

    @SerializedName("lat")
    private double lat;

    @SerializedName("lon")
    private double lon;


    public GamingRoom(){}

    public GamingRoom(Long id, String name, float rating, double lat, double lon) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.lat = lat;
        this.lon = lon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
