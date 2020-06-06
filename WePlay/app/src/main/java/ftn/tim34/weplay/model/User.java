package ftn.tim34.weplay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("firstName")
    @Expose
    private String firstName;

    @SerializedName("lastName")
    @Expose
    private String lastName;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("gamingSkill")
    @Expose
    private float gamingSkill;

    public User(){}

    public User(String name, String surname, String email, String password, float skill) {
        this.firstName = name;
        this.lastName = surname;
        this.email = email;
        this.password = password;
        this.gamingSkill = skill;
    }

    public String getName() {
        return firstName;
    }

    public void setName(String name) {
        this.firstName = name;
    }

    public String getSurname() {
        return lastName;
    }

    public void setSurname(String surname) {
        this.lastName = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getSkill() {
        return gamingSkill;
    }

    public void setSkill(float skill) {
        this.gamingSkill = skill;
    }
}
