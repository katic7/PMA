package ftn.tim34.weplay.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Review implements Serializable {

    @SerializedName("user_id")
    Long user_id;

    @SerializedName("user")
    String user;

    @SerializedName("comment")
    String comment;

    @SerializedName("rating")
    Float stars;

    public Review(String user, String comment, Float stars, Long user_id) {
        this.user = user;
        this.comment = comment;
        this.stars = stars;
        this.user_id = user_id;
    }

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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
