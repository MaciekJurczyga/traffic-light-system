package org.example.algorithm.intersection;

public class TrafficLightPhaseCalculator {
    private final IntersectionController intersectionController;
    private final TrafficLightPhaseStrategy strategy;

    public TrafficLightPhaseCalculator(IntersectionController intersectionController, TrafficLightPhaseStrategy strategy){
        this.intersectionController = intersectionController;
        this.strategy = strategy;
    }

    public TrafficLightPhase calculateBestPhase() {
        return strategy.calculatePhase(intersectionController.getVehiclesPerLaneMap());
    }

}
