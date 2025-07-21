package org.example.algorithm.intersection;

import org.example.util.Direction;

public enum LaneType {
    LEFT,
    STRAIGHT_OR_RIGHT;

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
