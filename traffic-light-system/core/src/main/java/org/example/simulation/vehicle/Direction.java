package org.example.simulation.vehicle;

import java.util.Objects;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

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
