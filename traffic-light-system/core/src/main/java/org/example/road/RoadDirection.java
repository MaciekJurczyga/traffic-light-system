package org.example.road;

import java.util.Objects;

public enum RoadDirection {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public static RoadDirection fromString(String direction) {
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
