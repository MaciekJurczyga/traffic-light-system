package org.example.algorithm.intersection;

import org.example.algorithm.vehicle.Vehicle;

import java.util.*;

public class TrafficTimeWaitPriorityPhaseStrategy implements TrafficLightPhaseStrategy{

    @Override
    public TrafficLightPhase calculatePhase(Map<LaneIdentifier, Queue<Vehicle>> laneQueues) {
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
