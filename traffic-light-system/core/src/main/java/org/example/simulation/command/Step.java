package org.example.simulation.command;

import org.example.simulation.intersection.IntersectionController;
import org.example.simulation.SimulationContext;
import org.example.simulation.intersection.TrafficLightController;
import org.example.simulation.intersection.TrafficLightPhase;
import org.example.simulation.vehicle.Vehicle;

import java.util.List;

public class Step implements Command{
    static int counter = 1;

    @Override
    public void executeCommand(SimulationContext simulationContext){
        TrafficLightController trafficLightController = simulationContext.getTrafficLightController();
        IntersectionController intersectionController = simulationContext.getIntersectionController();

        TrafficLightPhase currentGreenLightPhase = trafficLightController.updatePhase();
        List<Vehicle> moved = intersectionController.moveVehicles(currentGreenLightPhase);
        System.out.println("------------------");
        System.out.println("Step number: " + counter++);
        moved.forEach(vehicle -> System.out.println(vehicle.getVehicleId()));
        System.out.println("------------------");
    }
}
