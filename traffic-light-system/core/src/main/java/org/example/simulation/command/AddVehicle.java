package org.example.simulation.command;

import org.example.simulation.SimulationContext;
import org.example.simulation.vehicle.Vehicle;

public class AddVehicle implements Command {

    private final Vehicle vehicle;


    /**
     * AddVehicle command implementation adds vehicle to proper lane
     * @param simulationContext context of simulation, hold traffic load and traffic light controllers
     */
    @Override
    public void executeCommand(SimulationContext simulationContext){
        simulationContext.addVehicleIdToContext(vehicle.getVehicleId());
        simulationContext.getIntersectionTrafficController().addVehicleToProperLane(vehicle);
    }

    public AddVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
    }
}
