package org.example.simulation.intersection;

import java.util.Map;
import java.util.Set;

/**
 * Record which holds definition of each lane
 * @param laneIdentifiers lanes that are part of the phase
 * @param trafficLightTypeByLaneIdentifierMap what type of traffic light is on given lane, could be regular or conditional
 */
public record TrafficLightPhase(Set<LaneIdentifier> laneIdentifiers,
                                Map<LaneIdentifier, TrafficLightType> trafficLightTypeByLaneIdentifierMap) {

    public boolean isLaneConditional(LaneIdentifier laneIdentifier){
        return trafficLightTypeByLaneIdentifierMap.get(laneIdentifier).equals(TrafficLightType.CONDITIONAL_RIGHT);
    }

    public  boolean isLaneRegular(LaneIdentifier laneIdentifier){
        return trafficLightTypeByLaneIdentifierMap.get(laneIdentifier).equals(TrafficLightType.REGULAR);
    }
}