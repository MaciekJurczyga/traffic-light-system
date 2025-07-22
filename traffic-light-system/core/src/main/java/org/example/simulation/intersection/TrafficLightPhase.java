package org.example.simulation.intersection;

import java.util.Set;

/**
 * Record which holds definition of each lane
 * @param laneIdentifiers lanes that are part of the phase
 */
public record TrafficLightPhase(Set<LaneIdentifier> laneIdentifiers) { }