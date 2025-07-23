package org.example.response;

import org.example.simulation.vehicle.Vehicle;

import java.util.List;

public class StepStatus {

    private List<String> leftVehicles;

    private StepStatus(List<String> leftVehicles){
        this.leftVehicles = leftVehicles;
    }

    public List<String> getLeftVehicles(){
        return leftVehicles;
    }

    public static StepStatus from(List<Vehicle> vehicles){
        List<String> ids = vehicles.stream()
                .map(Vehicle::getVehicleId)
                .toList();
        return new StepStatus(ids);
    }

    @Override
    public String toString() {
        return "StepStatus{leftVehicles=" + leftVehicles + "}";
    }
}
