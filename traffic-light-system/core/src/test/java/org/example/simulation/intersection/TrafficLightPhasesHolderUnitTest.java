package org.example.simulation.intersection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TrafficLightPhasesHolderUnitTest {

    @Test
    @DisplayName("Holder class should create exactly 8 traffic light phases")
    void shouldCreate8TrafficLightPhases(){
        assertThat(TrafficLightPhasesHolder
                .getAllTrafficLightPhases()
                .size())
                .isEqualTo(8);
    }
}