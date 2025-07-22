package org.example.simulation.intersection.strategy;

import org.example.simulation.intersection.LaneIdentifier;
import org.example.simulation.intersection.TrafficLightPhase;
import org.example.simulation.intersection.TrafficLightPhasesHolder;
import org.example.simulation.vehicle.Vehicle;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Intelligent implementation of TrafficLightStrategy Interface
 */

public class IntelligentTrafficLoadBasedStrategy implements TrafficLightPhaseStrategy{

    /**
     * Calculates best phase basing on
     *   1) total cars to go on given phase
     *   2) waiting time of cars to go on given phase
     *   3) Lane priority
     *
     * @param laneQueues - map of Lane - Vehicles on that lane
     * @return best TrafficLightPhase
     */
    @Override
    public TrafficLightPhase calculateBestPhase(Map<LaneIdentifier, Queue<Vehicle>> laneQueues) {
        return TrafficLightPhasesHolder.getAllTrafficLightPhases().stream()
                .max(Comparator.comparingLong(phase -> calculatePhaseScore(phase, laneQueues)))
                .orElse(null);
    }

    // returns score for given phase.
    private long calculatePhaseScore(TrafficLightPhase phase, Map<LaneIdentifier, Queue<Vehicle>> laneQueues) {
        return phase.laneIdentifiers().stream()
                .mapToLong(identifier -> {
                    Queue<Vehicle> vehicles = laneQueues.getOrDefault(identifier, new LinkedList<>());
                    return getLanePriority(identifier, vehicles)
                            + calculateTotalWaitingTimeOfVehicles(vehicles)
                            + vehicles.size();
                })
                .sum();
    }

    // sums waiting time of each vehicle on given lane
    private int calculateTotalWaitingTimeOfVehicles(Queue<Vehicle> vehicles) {
        return vehicles.stream()
                .mapToInt(Vehicle::getWaitingTime)
                .sum();
    }

    // returns lane priority, returns 0 when lane is empty not to produce false positive effects
    private int getLanePriority(LaneIdentifier laneIdentifier, Queue<Vehicle> vehiclesOnLane){
        if(vehiclesOnLane.isEmpty()){
            return 0;
        }
        return laneIdentifier.laneType().getPriority();
    }

}
