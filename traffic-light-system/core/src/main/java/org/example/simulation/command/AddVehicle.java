package org.example.simulation.command;

import org.example.simulation.SimulationContext;
import org.example.simulation.vehicle.Vehicle;

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
