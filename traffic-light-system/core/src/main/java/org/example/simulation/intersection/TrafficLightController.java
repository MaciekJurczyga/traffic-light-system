package org.example.simulation.intersection;

import org.example.simulation.intersection.strategy.TrafficLightPhaseCalculator;

/**
 * Class which controls TrafficLightPhases
 */
public class TrafficLightController {

    private TrafficLightPhase currentGreenLightPhase;
    private final TrafficLightPhaseCalculator calculator;

    public TrafficLightController(TrafficLightPhaseCalculator calculator){
        this.calculator = calculator;
        this.currentGreenLightPhase = calculator.calculateBestPhase();
    }

    /**
     * Updates current green light phase basing on phase calculator
     * @return current green light phase
     */
    public TrafficLightPhase updatePhase() {
        this.currentGreenLightPhase = calculator.calculateBestPhase();
        return this.currentGreenLightPhase;
    }

}
