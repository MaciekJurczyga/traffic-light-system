package org.example.simulation.intersection.strategy;

import org.example.simulation.intersection.IntersectionTrafficController;
import org.example.simulation.intersection.LaneIdentifier;
import org.example.simulation.intersection.TrafficLightPhase;
import org.example.simulation.vehicle.Vehicle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;
import java.util.Queue;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class TrafficLightPhaseCalculatorUnitTest {

    @Test
    @DisplayName("should delegate phase calculation to strategy using current vehicle map")
    void shouldCalculateBestPhaseUsingStrategy() {
        // given
        IntersectionTrafficController controller = mock(IntersectionTrafficController.class);
        TrafficLightPhaseStrategy strategy = mock(TrafficLightPhaseStrategy.class);

        TrafficLightPhase expectedPhase = mock(TrafficLightPhase.class);
        Map<LaneIdentifier, Queue<Vehicle>> vehicleMap = Collections.emptyMap();

        when(controller.getVehiclesPerLaneMap()).thenReturn(vehicleMap);
        when(strategy.calculateBestPhase(vehicleMap)).thenReturn(expectedPhase);

        TrafficLightPhaseCalculator calculator = new TrafficLightPhaseCalculator(controller, strategy);

        // when
        TrafficLightPhase actual = calculator.calculateBestPhase();

        // then
        assertThat(actual).isEqualTo(expectedPhase);
        verify(controller).getVehiclesPerLaneMap();
        verify(strategy).calculateBestPhase(vehicleMap);
    }
}