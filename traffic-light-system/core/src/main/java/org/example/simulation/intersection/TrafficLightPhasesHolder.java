package org.example.simulation.intersection;

import org.example.simulation.vehicle.Direction;

import java.util.Map;
import java.util.Set;

import static org.example.simulation.intersection.TrafficLightType.*;


/**
 * Class that holds information about all available phases in our intersection.
 */
public class TrafficLightPhasesHolder {

    private static final Set<TrafficLightPhase> TRAFFIC_LIGHT_PHASES = Set.of(
            createPhase(Map.of(
                    new LaneIdentifier(Direction.NORTH, LaneType.STRAIGHT_OR_RIGHT), REGULAR,
                    new LaneIdentifier(Direction.SOUTH, LaneType.STRAIGHT_OR_RIGHT), REGULAR
            )),
            createPhase(Map.of(
                    new LaneIdentifier(Direction.WEST, LaneType.STRAIGHT_OR_RIGHT), REGULAR,
                    new LaneIdentifier(Direction.EAST, LaneType.STRAIGHT_OR_RIGHT), REGULAR
            )),
            createPhase(Map.of(
                    new LaneIdentifier(Direction.WEST, LaneType.LEFT), REGULAR,
                    new LaneIdentifier(Direction.EAST, LaneType.LEFT), REGULAR,
                    new LaneIdentifier(Direction.NORTH, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT,
                    new LaneIdentifier(Direction.SOUTH, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT
            )),
            createPhase(Map.of(
                    new LaneIdentifier(Direction.NORTH, LaneType.LEFT), REGULAR,
                    new LaneIdentifier(Direction.SOUTH, LaneType.LEFT), REGULAR,
                    new LaneIdentifier(Direction.WEST, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT,
                    new LaneIdentifier(Direction.EAST, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT
            )),
            createPhase(Map.of(
                    new LaneIdentifier(Direction.NORTH, LaneType.LEFT), REGULAR,
                    new LaneIdentifier(Direction.NORTH, LaneType.STRAIGHT_OR_RIGHT), REGULAR,
                    new LaneIdentifier(Direction.EAST, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT,
                    new LaneIdentifier(Direction.SOUTH, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT

            )),
            createPhase(Map.of(
                    new LaneIdentifier(Direction.SOUTH, LaneType.LEFT), REGULAR,
                    new LaneIdentifier(Direction.SOUTH, LaneType.STRAIGHT_OR_RIGHT), REGULAR,
                    new LaneIdentifier(Direction.WEST, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT,
                    new LaneIdentifier(Direction.NORTH, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT
            )),
            createPhase(Map.of(
                    new LaneIdentifier(Direction.EAST, LaneType.LEFT), REGULAR,
                    new LaneIdentifier(Direction.EAST, LaneType.STRAIGHT_OR_RIGHT), REGULAR,
                    new LaneIdentifier(Direction.WEST, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT,
                    new LaneIdentifier(Direction.SOUTH, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT
            )),
            createPhase(Map.of(
                    new LaneIdentifier(Direction.WEST, LaneType.LEFT), REGULAR,
                    new LaneIdentifier(Direction.WEST, LaneType.STRAIGHT_OR_RIGHT), REGULAR,
                    new LaneIdentifier(Direction.EAST, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT,
                    new LaneIdentifier(Direction.NORTH, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT
            )),

            createPhase(Map.of(
                    new LaneIdentifier(Direction.SOUTH, LaneType.STRAIGHT_OR_RIGHT), REGULAR,
                    new LaneIdentifier(Direction.WEST, LaneType.LEFT), REGULAR,
                    new LaneIdentifier(Direction.WEST, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT,
                    new LaneIdentifier(Direction.NORTH, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT
            )),

            createPhase(Map.of(
                    new LaneIdentifier(Direction.EAST, LaneType.STRAIGHT_OR_RIGHT), REGULAR,
                    new LaneIdentifier(Direction.SOUTH, LaneType.LEFT), REGULAR,
                    new LaneIdentifier(Direction.SOUTH, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT,
                    new LaneIdentifier(Direction.WEST, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT
            )),

            createPhase(Map.of(
                    new LaneIdentifier(Direction.NORTH, LaneType.STRAIGHT_OR_RIGHT), REGULAR,
                    new LaneIdentifier(Direction.EAST, LaneType.LEFT), REGULAR,
                    new LaneIdentifier(Direction.EAST, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT,
                    new LaneIdentifier(Direction.SOUTH, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT
            )),

            createPhase(Map.of(
                    new LaneIdentifier(Direction.WEST, LaneType.STRAIGHT_OR_RIGHT), REGULAR,
                    new LaneIdentifier(Direction.NORTH, LaneType.LEFT), REGULAR,
                    new LaneIdentifier(Direction.NORTH, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT,
                    new LaneIdentifier(Direction.EAST, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT
            )),
            createPhase(Map.of(
                    new LaneIdentifier(Direction.NORTH, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT,
                    new LaneIdentifier(Direction.EAST, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT,
                    new LaneIdentifier(Direction.SOUTH, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT,
                    new LaneIdentifier(Direction.WEST, LaneType.STRAIGHT_OR_RIGHT), CONDITIONAL_RIGHT
            ))
    );

    private static TrafficLightPhase createPhase(Map<LaneIdentifier, TrafficLightType> laneMap) {
        return new TrafficLightPhase(laneMap.keySet(), laneMap);
    }

    /**
     * Returns all traffic light phases.
     */
    public static Set<TrafficLightPhase> getAllTrafficLightPhases() {
        return TRAFFIC_LIGHT_PHASES;
    }
}

