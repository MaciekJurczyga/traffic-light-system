package org.example.algorithm.commands;

import org.example.algorithm.intersection.Intersection;
import org.example.algorithm.intersection.TrafficLightController;

public class Step implements Command{

    @Override
    public void executeCommand(){
        Intersection intersection = Intersection.getInstance();
        TrafficLightController controller = TrafficLightController.getInstance();
        controller.calculateBestPhase();
        intersection.moveVehiclesWithGreenLight();
    }

    @Override
    public String toString() {
        return "Step{}";
    }
}
