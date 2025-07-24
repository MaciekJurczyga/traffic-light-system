package org.example.simulation.intersection;

import org.example.simulation.vehicle.Direction;

/**
 * Unique lane identifier, describes each lane in intersection
 *
 * @param from - what direction this lane comes from
 * @param laneType - is it left-turn or straight_right lane
 */
public record LaneIdentifier(Direction from, LaneType laneType) {}
