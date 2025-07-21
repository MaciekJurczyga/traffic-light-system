package org.example.vehicle;

import org.example.road.RoadDirection;

public class Vehicle {

    private String vehicleId;
    private RoadDirection startRoad;
    private RoadDirection endRoad;

    public Vehicle(String vehicleId, String startRoad, String endRoad){
        this.vehicleId = vehicleId;
        this.startRoad = RoadDirection.fromString(startRoad);
        this.endRoad = RoadDirection.fromString(endRoad);
    }

    public String getVehicleId(){
        return vehicleId;
    }
}
