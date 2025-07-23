package org.example.simulation;

import org.example.simulation.intersection.IntersectionTrafficController;
import org.example.simulation.intersection.TrafficLightController;
import org.example.simulation.intersection.strategy.IntelligentTrafficLoadBasedStrategy;
import org.example.simulation.intersection.strategy.TrafficLightPhaseCalculator;
import org.example.simulation.intersection.strategy.QueueLengthBasedStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Class holding simulation context, common for each command of one simulation
 */
public class SimulationContext {

    private final List<String> vehiclesId = new ArrayList<>();
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

    public void addVehicleIdToContext(String vehicleId){
        if(vehiclesId.contains(vehicleId)){
            throw new IllegalArgumentException("Duplicated vehicle id: " + vehicleId);
        }
        vehiclesId.add(vehicleId);
    }
}
