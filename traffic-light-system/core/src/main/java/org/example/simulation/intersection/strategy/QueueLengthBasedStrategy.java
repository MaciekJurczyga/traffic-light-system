package org.example.simulation.intersection.strategy;

import org.example.simulation.intersection.LaneIdentifier;
import org.example.simulation.intersection.TrafficLightPhase;
import org.example.simulation.intersection.TrafficLightPhasesHolder;
import org.example.simulation.vehicle.Vehicle;

import java.util.*;

public class QueueLengthBasedStrategy implements TrafficLightPhaseStrategy {

    @Override
    public TrafficLightPhase calculateBestPhase(Map<LaneIdentifier, Queue<Vehicle>> laneQueues) {
        return TrafficLightPhasesHolder.getAllTrafficLightPhases().stream()
                .max(Comparator.comparingInt(phase ->
                        phase.laneIdentifiers().stream()
                                .map(laneQueues::get)
                                .filter(Objects::nonNull)
                                .mapToInt(Queue::size)
                                .sum()
                ))
                .orElseThrow();
    }
}
