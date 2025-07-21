package org.example.algorithm.intersection;

import org.example.util.Direction;

import java.util.Set;

import static org.example.algorithm.intersection.LaneType.LEFT;
import static org.example.algorithm.intersection.LaneType.STRAIGHT_OR_RIGHT;
import static org.example.util.Direction.*;
import static org.example.util.Direction.SOUTH;

public class TrafficLightPhasesHolder {

    private static final Set<TrafficLightPhase> TRAFFIC_LIGHT_PHASES = Set.of(
            createPhase(NORTH, STRAIGHT_OR_RIGHT, SOUTH, STRAIGHT_OR_RIGHT),
            createPhase(WEST, STRAIGHT_OR_RIGHT, EAST, STRAIGHT_OR_RIGHT),
            createPhase(WEST, LEFT, EAST, LEFT),
            createPhase(NORTH, LEFT, SOUTH, LEFT)
    );

    private static TrafficLightPhase createPhase(Direction dir1, LaneType type1, Direction dir2, LaneType type2) {
        return new TrafficLightPhase(Set.of(
                new LaneIdentifier(dir1, type1),
                new LaneIdentifier(dir2, type2)
        ));
    }

    public static Set<TrafficLightPhase> getAllTrafficLightPhases() {
        return TRAFFIC_LIGHT_PHASES;
    }
}