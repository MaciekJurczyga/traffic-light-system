package org.example.simulation.intersection;

import org.example.simulation.intersection.parameters.TunableParameters;
import org.example.simulation.vehicle.Direction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LaneTypeUnitTest {

    @Test
    @DisplayName("LEFT lane type should have correct priority")
    void testLeftLanePriority() {
        assertThat(LaneType.LEFT.getPriority())
                .isEqualTo(TunableParameters.LEFT_LINE_PRIORITY);
    }

    @Test
    @DisplayName("STRAIGHT_OR_RIGHT lane type should have correct priority")
    void testStraightOrRightLanePriority() {
        assertThat(LaneType.STRAIGHT_OR_RIGHT.getPriority())
                .isEqualTo(TunableParameters.STRAIGHT_OR_RIGHT_LANE_PRIORITY);
    }

    @Test
    @DisplayName("Determine LEFT lane correctly for left turn")
    void testDetermineLeftLane() {
        LaneType lane = LaneType.determineLaneType(Direction.NORTH, Direction.EAST);
        assertThat(lane).isEqualTo(LaneType.LEFT);
    }

    @Test
    @DisplayName("Determine STRAIGHT_OR_RIGHT lane correctly for straight and right turn")
    void testDetermineStraightLane() {
        LaneType lane = LaneType.determineLaneType(Direction.NORTH, Direction.SOUTH);
        assertThat(lane).isEqualTo(LaneType.STRAIGHT_OR_RIGHT);
    }

    @Test
    @DisplayName("Determine STRAIGHT_OR_RIGHT lane correctly for right turn")
    void testDetermineRightLane() {
        LaneType lane = LaneType.determineLaneType(Direction.NORTH, Direction.WEST);
        assertThat(lane).isEqualTo(LaneType.STRAIGHT_OR_RIGHT);
    }

    @Test
    @DisplayName("Throw exception on invalid turn (U-turn)")
    void testDetermineLaneThrowsOnInvalidTurn() {
        assertThatThrownBy(() ->
                LaneType.determineLaneType(Direction.NORTH, Direction.NORTH)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid turn");
    }
}