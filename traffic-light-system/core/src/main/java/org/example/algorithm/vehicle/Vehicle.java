package org.example.algorithm.vehicle;

import org.example.util.Direction;

public class Vehicle {

    private String vehicleId;
    private Direction startRoad;
    private Direction endRoad;
    private int waitingTime = 0;

    public Vehicle(String vehicleId, String startRoad, String endRoad){
        this.vehicleId = vehicleId;
        this.startRoad = Direction.fromString(startRoad);
        this.endRoad = Direction.fromString(endRoad);
    }

    public String getVehicleId(){
        return vehicleId;
    }

    public Direction getStartRoad(){
        return startRoad;
    }

    public Direction getEndRoad(){
        return endRoad;
    }
}
