package ftn.tim34.weplay.model;

import java.io.Serializable;

public class Review implements Serializable {
    String user;
    String comment;
    Float stars;

    public Review(String user, String comment, Float stars) {
        this.user = user;
        this.comment = comment;
        this.stars = stars;
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
}
