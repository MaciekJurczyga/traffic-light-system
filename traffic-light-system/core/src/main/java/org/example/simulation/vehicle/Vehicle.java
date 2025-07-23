package org.example.simulation.vehicle;

import org.example.simulation.intersection.LaneType;
import org.example.simulation.intersection.TurnType;
import org.example.simulation.intersection.parameters.TunableParameters;

public class Vehicle {

    private final String vehicleId;
    private final Direction startRoad;
    private final Direction endRoad;
    private int waitingTime = 1;

    public Vehicle(String vehicleId, String startRoad, String endRoad){
        this.vehicleId = validateId(vehicleId);
        this.startRoad = Direction.fromString(startRoad);
        this.endRoad = Direction.fromString(endRoad);
    }

    private String validateId(String vehicleId){
        if(vehicleId != null && !vehicleId.isEmpty()){
            return vehicleId;
        }
        throw new IllegalArgumentException("vehicleId must not be null or empty!");
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

    public boolean turnsRight(){
        return startRoad.getTurnType(endRoad) == TurnType.RIGHT;
    }

    public int getWaitingTime(){
        return waitingTime;
    }

    public void increaseWaitingTime(){
        waitingTime += TunableParameters.WAITING_TIME_BONUS;
    }
}
