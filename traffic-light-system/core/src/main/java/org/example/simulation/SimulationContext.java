package org.example.simulation;

import org.example.simulation.intersection.IntersectionTrafficController;
import org.example.simulation.intersection.TrafficLightController;
import org.example.simulation.intersection.strategy.IntelligentTrafficLoadBasedStrategy;
import org.example.simulation.intersection.strategy.TrafficLightPhaseCalculator;
import org.example.simulation.intersection.strategy.QueueLengthBasedStrategy;

public class SimulationContext {

    private final IntersectionTrafficController intersectionTrafficController;
    private final TrafficLightController trafficLightController;

    public SimulationContext() {
        this.intersectionTrafficController = new IntersectionTrafficController();
        TrafficLightPhaseCalculator calculator = new TrafficLightPhaseCalculator(intersectionTrafficController, new IntelligentTrafficLoadBasedStrategy());
        this.trafficLightController = new TrafficLightController(calculator);
    }

    public IntersectionTrafficController getIntersectionTrafficController() {
        return intersectionTrafficController;
    }

    public TrafficLightController getTrafficLightController() {
        return trafficLightController;
    }
}
