package org.example.algorithm.intersection;

public class TrafficLightController {

    private TrafficLightPhase currentGreenLightPhase;
    private final TrafficLightPhaseCalculator calculator;

    public TrafficLightController(TrafficLightPhaseCalculator calculator){
        this.calculator = calculator;
        this.currentGreenLightPhase = calculator.calculateBestPhase();
    }

    public TrafficLightPhase updatePhase() {
        this.currentGreenLightPhase = calculator.calculateBestPhase();
        return this.currentGreenLightPhase;
    }

}
