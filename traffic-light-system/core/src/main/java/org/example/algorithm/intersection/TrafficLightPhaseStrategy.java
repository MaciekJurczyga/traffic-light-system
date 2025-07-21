package org.example.algorithm.intersection;

import org.example.algorithm.vehicle.Vehicle;

import java.util.Map;
import java.util.Queue;

public interface TrafficLightPhaseStrategy {

    TrafficLightPhase calculatePhase(Map<LaneIdentifier, Queue<Vehicle>> vehiclesPerLaneMap);
}
