package com.example.oatewologun.fmpoe.model;

public class SpatialModel {
    String level;
    String room_id;
    int proximities_space;
    int seating_density;
    int interior_visibility;
    int writable_surface;
    int furniture_comfort;
    int storage;
    int privacy;
    int provision;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public int getProximities_space() {
        return proximities_space;
    }

    public void setProximities_space(int proximities_space) {
        this.proximities_space = proximities_space;
    }

    public int getSeating_density() {
        return seating_density;
    }

    public void setSeating_density(int seating_density) {
        this.seating_density = seating_density;
    }

    public int getInterior_visibility() {
        return interior_visibility;
    }

    public void setInterior_visibility(int interior_visibility) {
        this.interior_visibility = interior_visibility;
    }

    public int getWritable_surface() {
        return writable_surface;
    }

    public void setWritable_surface(int writable_surface) {
        this.writable_surface = writable_surface;
    }

    public int getFurniture_comfort() {
        return furniture_comfort;
    }

    public void setFurniture_comfort(int furniture_comfort) {
        this.furniture_comfort = furniture_comfort;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }

    public int getProvision() {
        return provision;
    }

    public void setProvision(int provision) {
        this.provision = provision;
    }
}
