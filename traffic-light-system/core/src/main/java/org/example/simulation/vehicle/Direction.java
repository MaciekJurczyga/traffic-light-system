package org.example.simulation.vehicle;

import java.util.Objects;

/**
 * enum holding each world direction
 */
public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    /**
     * maps direction as string to direction as enum
     * @param direction as string
     * @return direction as enum
     */
    public static Direction fromString(String direction) {
        if (!Objects.nonNull(direction)) {
            throw new IllegalArgumentException("Direction string cannot be null");
        }

        return switch (direction.trim().toUpperCase()) {
            case "NORTH" -> NORTH;
            case "EAST" -> EAST;
            case "SOUTH" -> SOUTH;
            case "WEST" -> WEST;
            default -> throw new IllegalArgumentException("Unknown direction: " + direction);
        };
    }
}
