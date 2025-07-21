package org.example.simulation.intersection;

import java.util.Set;

public record TrafficLightPhase(Set<LaneIdentifier> laneIdentifiers) { }