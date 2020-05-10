package ftn.tim34.weplay.model;

import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable {
    String name;
    String game;
    Integer numberOfPlayers;
    Integer numberOfActivePlayers;
    String minimumSkillLevel;
    GameRoom gameRoom;
    Date deadline;

    public Event() {

    }

    public Event(String name, String game, Integer numberOfPlayers, Integer numberOfActivePlayers, GameRoom gameRoom, Date deadline, String minimumSkillLevel) {
        this.name = name;
        this.game = game;
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfActivePlayers = numberOfActivePlayers;
        this.gameRoom = gameRoom;
        this.deadline = deadline;
        this.minimumSkillLevel = minimumSkillLevel;
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

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public Integer getNumberOfActivePlayers() {
        return numberOfActivePlayers;
    }

    public void setNumberOfActivePlayers(Integer numberOfActivePlayers) {
        this.numberOfActivePlayers = numberOfActivePlayers;
    }

    public GameRoom getGameRoom() {
        return gameRoom;
    }

    public void setGameRoom(GameRoom gameRoom) {
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
}
