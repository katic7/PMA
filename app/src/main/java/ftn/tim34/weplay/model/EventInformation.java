package ftn.tim34.weplay.model;

import java.io.Serializable;
import java.util.Date;

public class EventInformation implements Serializable {

    private Date date;
    private String user;
    private String score;
    private String comment;

    public EventInformation(Date date, String user, String score, String comment) {
        this.date = date;
        this.user = user;
        this.score = score;
        this.comment = comment;
    }

    public EventInformation() {

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
