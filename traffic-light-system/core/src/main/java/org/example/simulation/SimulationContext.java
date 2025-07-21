package org.example.simulation;

import org.example.simulation.intersection.IntersectionController;
import org.example.simulation.intersection.TrafficLightController;
import org.example.simulation.intersection.strategy.TrafficLightPhaseCalculator;
import org.example.simulation.intersection.strategy.QueueLengthBasedStrategy;

public class SimulationContext {

    private final IntersectionController intersectionController;
    private final TrafficLightController trafficLightController;

    public SimulationContext() {
        this.intersectionController = new IntersectionController();
        TrafficLightPhaseCalculator calculator = new TrafficLightPhaseCalculator(intersectionController, new QueueLengthBasedStrategy());
        this.trafficLightController = new TrafficLightController(calculator);
    }

    public IntersectionController getIntersectionController() {
        return intersectionController;
    }

    public TrafficLightController getTrafficLightController() {
        return trafficLightController;
    }
}
