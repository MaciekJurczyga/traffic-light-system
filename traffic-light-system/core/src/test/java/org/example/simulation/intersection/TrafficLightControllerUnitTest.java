package org.example.simulation.intersection;

import org.example.simulation.intersection.strategy.TrafficLightPhaseCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TrafficLightControllerUnitTest {

    private TrafficLightPhaseCalculator calculator;
    private TrafficLightPhase phase1;
    private TrafficLightPhase phase2;

    @BeforeEach
    void setup() {
        calculator = mock(TrafficLightPhaseCalculator.class);
        phase1 = mock(TrafficLightPhase.class);
        phase2 = mock(TrafficLightPhase.class);
    }

    @Test
    @DisplayName("Default phase should be initialized after constructor call")
    void shouldInitializeWithPhaseFromCalculator() {
        when(calculator.calculateBestPhase()).thenReturn(phase1);

        TrafficLightController controller = new TrafficLightController(calculator);

        assertThat(controller.updatePhase()).isEqualTo(phase1);
        verify(calculator, times(2)).calculateBestPhase();
    }

    @Test
    @DisplayName("Calling update phase should update current phase")
    void shouldUpdatePhaseWhenCallingUpdatePhase() {
        when(calculator.calculateBestPhase())
                .thenReturn(phase1)
                .thenReturn(phase2);

        TrafficLightController controller = new TrafficLightController(calculator);

        TrafficLightPhase updated = controller.updatePhase();

        assertThat(updated).isEqualTo(phase2);
        verify(calculator, times(2)).calculateBestPhase();
    }
}