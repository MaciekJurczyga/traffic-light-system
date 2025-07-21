package org.example.algorithm.intersection;

public class SimulationContext {

    private final IntersectionController intersectionController;
    private final TrafficLightController trafficLightController;

    public SimulationContext() {
        this.intersectionController = new IntersectionController();
        TrafficLightPhaseCalculator calculator = new TrafficLightPhaseCalculator(intersectionController, new TrafficTimeWaitPriorityPhaseStrategy());
        this.trafficLightController = new TrafficLightController(calculator);
    }

    public IntersectionController getIntersectionController() {
        return intersectionController;
    }

    public TrafficLightController getTrafficLightController() {
        return trafficLightController;
    }
}
