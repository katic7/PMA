package ftn.tim34.weplay.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Review implements Serializable {

    @SerializedName("user_email")
    String user_email;

    @SerializedName("user")
    String user;

    @SerializedName("comment")
    String comment;

    @SerializedName("rating")
    Float stars;

    public Review(String user, String comment, Float stars, String user_email) {
        this.user = user;
        this.comment = comment;
        this.stars = stars;
        this.user_email = user_email;
    }

    public Review(){};

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Float getStars() {
        return stars;
    }

    public void setStars(Float stars) {
        this.stars = stars;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
