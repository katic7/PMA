package ftn.tim34.weplay.model;

import java.io.Serializable;

public class GameRoom implements Serializable {
    String name;
    Integer price_per_hour;
    String working_hours;
    String phoneNumber;
    Float rating;

    public GameRoom() {
        super();
    }

    public GameRoom(String name, Integer price_per_hour, String working_hours, String phoneNumber, Float rating) {
        super();
        this.name = name;
        this.price_per_hour = price_per_hour;
        this.working_hours = working_hours;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
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

    public Float getRating() {
        return this.rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

}
