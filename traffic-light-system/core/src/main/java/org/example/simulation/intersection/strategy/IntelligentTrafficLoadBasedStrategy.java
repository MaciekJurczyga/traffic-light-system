package org.example.simulation.intersection.strategy;

import org.example.simulation.intersection.LaneIdentifier;
import org.example.simulation.intersection.TrafficLightPhase;
import org.example.simulation.intersection.TrafficLightPhasesHolder;
import org.example.simulation.vehicle.Vehicle;

import java.util.*;

/**
 * Intelligent implementation of TrafficLightPhaseStrategy.
 * Decides the best phase based on:
 *   1) Total number of cars,
 *   2) Total waiting time,
 *   3) Lane priority.
 */
public class IntelligentTrafficLoadBasedStrategy implements TrafficLightPhaseStrategy {

    @Override
    public TrafficLightPhase calculateBestPhase(Map<LaneIdentifier, Queue<Vehicle>> laneQueues) {
        return TrafficLightPhasesHolder.getAllTrafficLightPhases().stream()
                .max(Comparator.comparingLong(phase -> calculatePhaseScore(phase, laneQueues)))
                .orElse(null);
    }

    private long calculatePhaseScore(TrafficLightPhase phase, Map<LaneIdentifier, Queue<Vehicle>> laneQueues) {
        long regularScore = calculateScoreForRegularLanes(phase, laneQueues);
        long conditionalScore = calculateScoreForConditionalLanes(phase, laneQueues);
        return regularScore + conditionalScore;
    }

    private long calculateScoreForRegularLanes(TrafficLightPhase phase, Map<LaneIdentifier, Queue<Vehicle>> laneQueues) {
        return phase.laneIdentifiers().stream()
                .filter(phase::isLaneRegular)
                .mapToLong(lane -> calculateLaneScore(lane, laneQueues.getOrDefault(lane, new LinkedList<>())))
                .sum();
    }

    private long calculateScoreForConditionalLanes(TrafficLightPhase phase, Map<LaneIdentifier, Queue<Vehicle>> laneQueues) {
        return phase.laneIdentifiers().stream()
                .filter(phase::isLaneConditional)
                .mapToLong(lane -> {
                    Queue<Vehicle> allVehicles = laneQueues.getOrDefault(lane, new LinkedList<>());
                    Queue<Vehicle> rightTurners = extractLeadingRightTurners(allVehicles);
                    return calculateLaneScore(lane, rightTurners);
                })
                .sum();
    }

    private Queue<Vehicle> extractLeadingRightTurners(Queue<Vehicle> vehicles) {
        Queue<Vehicle> result = new LinkedList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.turnsRight()) {
                result.add(vehicle);
            } else {
                break;
            }
        }
        return result;
    }

    private long calculateLaneScore(LaneIdentifier lane, Queue<Vehicle> vehicles) {
        if (vehicles.isEmpty()) return 0;

        int lanePriority = lane.laneType().getPriority();
        int totalWaitingTime = vehicles.stream().mapToInt(Vehicle::getWaitingTime).sum();
        int vehicleCount = vehicles.size();

        return lanePriority + totalWaitingTime + vehicleCount;
    }
}
