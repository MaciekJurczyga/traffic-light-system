package org.example.simulation.intersection;

import org.example.simulation.intersection.strategy.TrafficLightPhaseCalculator;

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
