package org.example.simulation.intersection.strategy;

import java.util.Map;
import java.util.Queue;
import org.example.simulation.intersection.LaneIdentifier;
import org.example.simulation.intersection.TrafficLightPhase;
import org.example.simulation.vehicle.Vehicle;

public interface TrafficLightPhaseStrategy {

  TrafficLightPhase calculateBestPhase(Map<LaneIdentifier, Queue<Vehicle>> vehiclesPerLaneMap);
}
