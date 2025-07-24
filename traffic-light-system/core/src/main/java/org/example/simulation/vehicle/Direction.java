package org.example.simulation.vehicle;

import java.util.Objects;
import org.example.simulation.intersection.TurnType;

/** enum holding each world direction */
public enum Direction {
  NORTH,
  EAST,
  SOUTH,
  WEST;

  /**
   * maps direction as string to direction as enum
   *
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

  public Direction leftFromIntersection() {
    return switch (this) {
      case NORTH -> EAST;
      case EAST -> SOUTH;
      case SOUTH -> WEST;
      case WEST -> NORTH;
    };
  }

  public Direction rightFromIntersection() {
    return switch (this) {
      case NORTH -> WEST;
      case WEST -> SOUTH;
      case SOUTH -> EAST;
      case EAST -> NORTH;
    };
  }

  public Direction straightFromIntersection() {
    return opposite();
  }

  public Direction opposite() {
    return switch (this) {
      case NORTH -> SOUTH;
      case SOUTH -> NORTH;
      case EAST -> WEST;
      case WEST -> EAST;
    };
  }

  public TurnType getTurnType(Direction to) {
    if (to == null) {
      throw new IllegalArgumentException("Directions cannot be null");
    }
    if (to == this.straightFromIntersection()) {
      return TurnType.STRAIGHT;
    } else if (to == this.leftFromIntersection()) {
      return TurnType.LEFT;
    } else if (to == this.rightFromIntersection()) {
      return TurnType.RIGHT;
    } else {
      throw new IllegalArgumentException("Invalid turn: " + this + " -> " + to);
    }
  }
}
