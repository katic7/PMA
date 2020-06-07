package ftn.tim34.weplay.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event implements Serializable {

    @SerializedName("name")
    String name;

    @SerializedName("game")
    String game;

    @SerializedName("numbOfPlayers")
    int numberOfPlayers;

    @SerializedName("participants")
    int numberOfActivePlayers;


    String minimumSkillLevel;

    @SerializedName("gameRoom")
    String gameRoom;

    @SerializedName("joinDeadline")
    Date deadline;

    @SerializedName("lat")
    double lat;

    @SerializedName("lon")
    double lon;


    List<EventInformation> news = new ArrayList<>();

    public Event() {

    }

    public Event(String name, String game, int numberOfPlayers, int numberOfActivePlayers, String gameRoom, Date deadline, String minimumSkillLevel) {
        this.name = name;
        this.game = game;
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfActivePlayers = numberOfActivePlayers;
        this.gameRoom = gameRoom;
        this.deadline = deadline;
        this.minimumSkillLevel = minimumSkillLevel;
    }


    public Event(String name, String game, int numberOfPlayers, int numberOfActivePlayers, String minimumSkillLevel, String gameRoom, Date deadline, List<EventInformation> news) {
        this.name = name;
        this.game = game;
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfActivePlayers = numberOfActivePlayers;
        this.minimumSkillLevel = minimumSkillLevel;
        this.gameRoom = gameRoom;
        this.deadline = deadline;
        this.news = news;
    }

    public List<EventInformation> getNews() {
        return news;
    }

    public void setNews(List<EventInformation> news) {
        this.news = news;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumberOfActivePlayers() {
        return numberOfActivePlayers;
    }

    public void setNumberOfActivePlayers(int numberOfActivePlayers) {
        this.numberOfActivePlayers = numberOfActivePlayers;
    }

    public String getGameRoom() {
        return gameRoom;
    }

    public void setGameRoom(String gameRoom) {
        this.gameRoom = gameRoom;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getMinimumSkillLevel() {
        return minimumSkillLevel;
    }

    public void setMinimumSkillLevel(String minimumSkillLevel) {
        this.minimumSkillLevel = minimumSkillLevel;
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
