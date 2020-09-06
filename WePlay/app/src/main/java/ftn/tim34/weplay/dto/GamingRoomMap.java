package ftn.tim34.weplay.dto;

import ftn.tim34.weplay.model.GamingRoom;

public class GamingRoomMap {

    private Long id;
    private String name;
    private double lat;
    private double lon;
    private String markerId;

    public GamingRoomMap(){}

    public GamingRoomMap(GamingRoom gr){
        this.id = gr.getId();
        this.lat = gr.getLat();
        this.lon = gr.getLon();
        this.name = gr.getName();
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

    public String getMarkerId() {
        return markerId;
    }

    public void setMarkerId(String markerId) {
        this.markerId = markerId;
    }
}
