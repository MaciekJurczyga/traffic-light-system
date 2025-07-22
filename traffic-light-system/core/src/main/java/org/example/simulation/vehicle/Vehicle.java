package org.example.simulation.vehicle;

import org.example.simulation.intersection.parameters.TunableParameters;

public class Vehicle {

    private final String vehicleId;
    private final Direction startRoad;
    private final Direction endRoad;
    private int waitingTime = 1;

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

    public int getWaitingTime(){
        return waitingTime;
    }

    public void increaseWaitingTime(){
        waitingTime += TunableParameters.WAITING_TIME_BONUS;
    }
}
