package org.example.simulation.command;

import org.example.simulation.intersection.IntersectionTrafficController;
import org.example.simulation.SimulationContext;
import org.example.simulation.intersection.TrafficLightController;
import org.example.simulation.intersection.TrafficLightPhase;
import org.example.simulation.vehicle.Vehicle;

import java.util.List;

public class Step implements Command{
    static int counter = 1;

    /**
     * Step implementation of command execution
     * Triggers calculation of new phase and then moves vehicles which have green light
     * @param simulationContext context of simulation, hold traffic load and traffic light controllers
     */
    @Override
    public void executeCommand(SimulationContext simulationContext){
        TrafficLightController trafficLightController = simulationContext.getTrafficLightController();
        IntersectionTrafficController intersectionTrafficController = simulationContext.getIntersectionTrafficController();

        TrafficLightPhase currentGreenLightPhase = trafficLightController.updatePhase();
        List<Vehicle> moved = intersectionTrafficController.moveVehicles(currentGreenLightPhase);
        System.out.println("------------------");
        System.out.println("Step number: " + counter++);
        moved.forEach(vehicle -> System.out.println(vehicle.getVehicleId()));
        System.out.println("------------------");
    }
}
