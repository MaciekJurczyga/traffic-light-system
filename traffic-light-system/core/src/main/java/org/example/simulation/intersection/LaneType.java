package org.example.simulation.intersection;

import org.example.simulation.intersection.parameters.TunableParameters;
import org.example.simulation.vehicle.Direction;


public enum LaneType {
    LEFT(TunableParameters.LEFT_LINE_PRIORITY),
    STRAIGHT_OR_RIGHT(TunableParameters.STRAIGHT_OR_RIGHT_LANE_PRIORITY);

    private final int priority;

    LaneType(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public static LaneType determineLaneType(Direction from, Direction to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Directions cannot be null");
        }

        int fromOrdinal = from.ordinal();
        int toOrdinal = to.ordinal();

        int diff = (toOrdinal - fromOrdinal + 4) % 4;

        return switch (diff) {
            case 0 -> throw new IllegalArgumentException("U-turns are not supported");
            case 1,2 -> LaneType.STRAIGHT_OR_RIGHT;
            case 3 -> LaneType.LEFT;
            default -> throw new IllegalStateException("Unexpected direction difference: " + diff);
        };
    }
}
