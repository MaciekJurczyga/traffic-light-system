package org.example.algorithm.commands;

import org.example.algorithm.intersection.SimulationContext;
import org.example.algorithm.vehicle.Vehicle;

public class AddVehicle implements Command {

    private Vehicle vehicle;

    @Override
    public void executeCommand(SimulationContext simulationContext){
        simulationContext.getIntersectionController().addVehicleToProperLane(vehicle);
    }

    public AddVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
    }
}
