package org.example.simulation.intersection;

import org.example.simulation.vehicle.Direction;

public record LaneIdentifier(Direction from, LaneType laneType) {
}
