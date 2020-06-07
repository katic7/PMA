package ftn.tim34.weplay.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GameRoom implements Serializable {
    @SerializedName("id")
    Long id;

    @SerializedName("name")
    String name;

    @SerializedName("capacity")
    private int capacity;

    @SerializedName("price_per_hour")
    int price_per_hour;

    @SerializedName("working_hours")
    String working_hours;

    @SerializedName("phoneNumber")
    String phoneNumber;

    @SerializedName("rating")
    float rating;

    @SerializedName("address")
    private Address address;

    @SerializedName("events")
    List<Event> events = new ArrayList<Event>();

    @SerializedName("reviews")
    List<Review> reviews = new ArrayList<Review>();

    public GameRoom() {
        super();
    }

    public GameRoom(Long id,String name, Integer price_per_hour, String working_hours, String phoneNumber, float rating, Address adr) {
        super();
        this.id = id;
        this.name = name;
        this.price_per_hour = price_per_hour;
        this.working_hours = working_hours;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
        this.address = adr;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice_per_hour() {
        return this.price_per_hour;
    }

    public void setPrice_per_hour(Integer pph) {
        this.price_per_hour = pph;
    }

    public String getWorking_hours() {
        return this.working_hours;
    }

    public void setWorking_hours(String working_hours) {
        this.working_hours = working_hours;
    }

    public String getPhone() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phone) {
        this.phoneNumber = phone;
    }

    public float getRating() {
        return this.rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setPrice_per_hour(int price_per_hour) {
        this.price_per_hour = price_per_hour;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
