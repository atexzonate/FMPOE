package com.example.oatewologun.fmpoe.model;

public class AmbientModel {
    String level;
    String room_id;
    int daylighting_adequacy;
    int provision_exterior;
    int lighting_control;
    int thermal_comfort;
    int temperature_control;
    int acoustic_comfort;
    int indoor_air;
    int finishes_colour;
    int building_design;

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

    public int getDaylighting_adequacy() {
        return daylighting_adequacy;
    }

    public void setDaylighting_adequacy(int daylighting_adequacy) {
        this.daylighting_adequacy = daylighting_adequacy;
    }

    public int getProvision_exterior() {
        return provision_exterior;
    }

    public void setProvision_exterior(int provision_exterior) {
        this.provision_exterior = provision_exterior;
    }

    public int getLighting_control() {
        return lighting_control;
    }

    public void setLighting_control(int lighting_control) {
        this.lighting_control = lighting_control;
    }

    public int getThermal_comfort() {
        return thermal_comfort;
    }

    public void setThermal_comfort(int thermal_comfort) {
        this.thermal_comfort = thermal_comfort;
    }

    public int getTemperature_control() {
        return temperature_control;
    }

    public void setTemperature_control(int temperature_control) {
        this.temperature_control = temperature_control;
    }

    public int getAcoustic_comfort() {
        return acoustic_comfort;
    }

    public void setAcoustic_comfort(int acoustic_comfort) {
        this.acoustic_comfort = acoustic_comfort;
    }

    public int getIndoor_air() {
        return indoor_air;
    }

    public void setIndoor_air(int indoor_air) {
        this.indoor_air = indoor_air;
    }

    public int getFinishes_colour() {
        return finishes_colour;
    }

    public void setFinishes_colour(int finishes_colour) {
        this.finishes_colour = finishes_colour;
    }

    public int getBuilding_design() {
        return building_design;
    }

    public void setBuilding_design(int building_design) {
        this.building_design = building_design;
    }
}
