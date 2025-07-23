package org.example.simulation.intersection.strategy;

import org.example.simulation.intersection.TrafficLightType;
import org.example.simulation.intersection.LaneIdentifier;
import org.example.simulation.intersection.LaneType;
import org.example.simulation.intersection.TrafficLightPhase;
import org.example.simulation.intersection.TrafficLightPhasesHolder;
import org.example.simulation.vehicle.Direction;
import org.example.simulation.vehicle.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class IntelligentTrafficLoadBasedStrategyUnitTest {

    private IntelligentTrafficLoadBasedStrategy strategy;

    @BeforeEach
    void setup() {
        strategy = new IntelligentTrafficLoadBasedStrategy();
    }

    @Test
    @DisplayName("should select the phase with the highest score (based on vehicle waiting time)")
    void shouldSelectPhaseWithHighestScore() {
        // given
        LaneIdentifier lowScoreLane = new LaneIdentifier(Direction.NORTH, LaneType.STRAIGHT_OR_RIGHT);
        LaneIdentifier highScoreLane = new LaneIdentifier(Direction.SOUTH, LaneType.LEFT);

        TrafficLightPhase lowScorePhase = new TrafficLightPhase(
                Set.of(lowScoreLane),
                Map.of(lowScoreLane, TrafficLightType.REGULAR)
        );

        TrafficLightPhase highScorePhase = new TrafficLightPhase(
                Set.of(highScoreLane),
                Map.of(highScoreLane, TrafficLightType.REGULAR)
        );

        Vehicle low = mock(Vehicle.class);
        when(low.getWaitingTime()).thenReturn(1);

        Vehicle high = mock(Vehicle.class);
        when(high.getWaitingTime()).thenReturn(20);

        Map<LaneIdentifier, Queue<Vehicle>> laneQueues = Map.of(
                lowScoreLane, new LinkedList<>(List.of(low)),
                highScoreLane, new LinkedList<>(List.of(high))
        );

        try (MockedStatic<TrafficLightPhasesHolder> mocked = mockStatic(TrafficLightPhasesHolder.class)) {
            mocked.when(TrafficLightPhasesHolder::getAllTrafficLightPhases)
                    .thenReturn(Set.of(lowScorePhase, highScorePhase));

            // when
            TrafficLightPhase result = strategy.calculateBestPhase(laneQueues);

            // then
            assertThat(result).isEqualTo(highScorePhase);
        }
    }

    @Test
    @DisplayName("should count only leading right-turning vehicles for conditional right lane")
    void shouldConsiderOnlyLeadingRightTurnersInConditionalLane() {
        // given
        LaneIdentifier conditionalLane = new LaneIdentifier(Direction.NORTH, LaneType.STRAIGHT_OR_RIGHT);

        TrafficLightPhase conditionalPhase = new TrafficLightPhase(
                Set.of(conditionalLane),
                Map.of(conditionalLane, TrafficLightType.CONDITIONAL_RIGHT)
        );

        Vehicle right1 = mock(Vehicle.class);
        when(right1.getWaitingTime()).thenReturn(5);
        when(right1.turnsRight()).thenReturn(true);

        Vehicle right2 = mock(Vehicle.class);
        when(right2.getWaitingTime()).thenReturn(5);
        when(right2.turnsRight()).thenReturn(true);

        Vehicle nonRight = mock(Vehicle.class);
        when(nonRight.getWaitingTime()).thenReturn(100);
        when(nonRight.turnsRight()).thenReturn(false);

        Queue<Vehicle> vehicles = new LinkedList<>(List.of(right1, right2, nonRight));

        Map<LaneIdentifier, Queue<Vehicle>> laneQueues = Map.of(
                conditionalLane, vehicles
        );

        try (MockedStatic<TrafficLightPhasesHolder> mocked = mockStatic(TrafficLightPhasesHolder.class)) {
            mocked.when(TrafficLightPhasesHolder::getAllTrafficLightPhases)
                    .thenReturn(Set.of(conditionalPhase));

            // when
            TrafficLightPhase result = strategy.calculateBestPhase(laneQueues);

            assertThat(result).isEqualTo(conditionalPhase);
        }
    }

}
