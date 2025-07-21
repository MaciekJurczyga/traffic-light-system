package org.example.algorithm.commands;

import org.example.algorithm.intersection.Intersection;
import org.example.algorithm.vehicle.Vehicle;

public class AddVehicle implements Command {

    private Vehicle vehicle;

    @Override
    public void executeCommand(){
        Intersection intersection = Intersection.getInstance();
        intersection.addVehicleToQueue(vehicle);

    }

    public AddVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
    }

    public String toString() {
        return "AddVehicle{" +
                "vehicleId='" + vehicle.getVehicleId() + '\'' +
                '}';
    }
}
