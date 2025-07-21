package org.example.algorithm.commands;

import org.example.algorithm.intersection.IntersectionController;
import org.example.algorithm.intersection.SimulationContext;
import org.example.algorithm.intersection.TrafficLightController;
import org.example.algorithm.intersection.TrafficLightPhase;
import org.example.algorithm.vehicle.Vehicle;

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
