package org.example.simulation.intersection.strategy;

import org.example.simulation.intersection.IntersectionController;
import org.example.simulation.intersection.TrafficLightPhase;

public class TrafficLightPhaseCalculator {
    private final IntersectionController intersectionController;
    private final TrafficLightPhaseStrategy strategy;

    public TrafficLightPhaseCalculator(IntersectionController intersectionController, TrafficLightPhaseStrategy strategy){
        this.intersectionController = intersectionController;
        this.strategy = strategy;
    }

    public TrafficLightPhase calculateBestPhase() {
        return strategy.calculateBestPhase(intersectionController.getVehiclesPerLaneMap());
    }

}
