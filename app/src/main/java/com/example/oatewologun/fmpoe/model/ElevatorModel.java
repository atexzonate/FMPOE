package com.example.oatewologun.fmpoe.model;

public class ElevatorModel {
    String level;

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

    public int getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(int maintenance) {
        this.maintenance = maintenance;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getElevator_lobbies() {
        return elevator_lobbies;
    }

    public void setElevator_lobbies(int elevator_lobbies) {
        this.elevator_lobbies = elevator_lobbies;
    }

    public int getElevator_design() {
        return elevator_design;
    }

    public void setElevator_design(int elevator_design) {
        this.elevator_design = elevator_design;
    }

    public int getOverall_quality() {
        return overall_quality;
    }

    public void setOverall_quality(int overall_quality) {
        this.overall_quality = overall_quality;
    }

    public int getSpeed_level() {
        return speed_level;
    }

    public void setSpeed_level(int speed_level) {
        this.speed_level = speed_level;
    }

    String room_id;
    int maintenance;
    int number;
    int elevator_lobbies;
    int elevator_design;
    int overall_quality;
    int speed_level;

}
