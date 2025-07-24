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
    TurnType turn = from.getTurnType(to);

    return switch (turn) {
      case LEFT -> LaneType.LEFT;
      case RIGHT, STRAIGHT -> LaneType.STRAIGHT_OR_RIGHT;
    };
  }
}
