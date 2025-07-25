package org.example.simulation.intersection;

/**
 * Enum which defines Traffic Light Type on given lane for given traffic light phase
 */
public enum TrafficLightType {
  // No collision traffic light
  REGULAR,
  // Conditional right - conditional right turn from Straight or Right lane
  CONDITIONAL_RIGHT
}
