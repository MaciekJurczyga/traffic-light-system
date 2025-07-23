package org.example.simulation.intersection;


import org.example.simulation.vehicle.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class TrafficLightPhaseUnitTest {

    @Test
    @DisplayName("should return true for CONDITIONAL_RIGHT light and false for REGULAR")
    void shouldReturnTrueForConditionalLight() {
        LaneIdentifier lane = new LaneIdentifier(Direction.NORTH, LaneType.STRAIGHT_OR_RIGHT);

        TrafficLightPhase phase = new TrafficLightPhase(
                Set.of(lane),
                Map.of(lane, TrafficLightType.CONDITIONAL_RIGHT)
        );

        assertThat(phase.isLaneConditional(lane)).isTrue();
        assertThat(phase.isLaneRegular(lane)).isFalse();
    }

    @Test
    @DisplayName("should return true for REGULAR light and false for CONDITIONAL_RIGHT")
    void shouldReturnTrueForRegularLight() {
        LaneIdentifier lane = new LaneIdentifier(Direction.EAST, LaneType.LEFT);

        TrafficLightPhase phase = new TrafficLightPhase(
                Set.of(lane),
                Map.of(lane, TrafficLightType.REGULAR)
        );

        assertThat(phase.isLaneRegular(lane)).isTrue();
        assertThat(phase.isLaneConditional(lane)).isFalse();
    }
}