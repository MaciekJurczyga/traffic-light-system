package org.example.simulation.intersection;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TrafficLightPhasesHolderUnitTest {

  @Test
  @DisplayName("Holder class should create exactly 13 traffic light phases")
  void shouldCreate8TrafficLightPhases() {
    assertThat(TrafficLightPhasesHolder.getAllTrafficLightPhases().size()).isEqualTo(13);
  }
}
