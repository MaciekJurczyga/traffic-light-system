package org.example.simulation;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SimulationContextTest {

  @Test
  @DisplayName("should create context with non-null controllers")
  void shouldCreateNonNullControllers() {
    // when
    SimulationContext context = new SimulationContext();

    // then
    assertThat(context.getIntersectionTrafficController()).isNotNull();
    assertThat(context.getTrafficLightController()).isNotNull();
  }

  @Test
  @DisplayName("should add unique vehicle ID to context")
  void shouldAddUniqueVehicleId() {
    // given
    SimulationContext context = new SimulationContext();

    // when
    context.addVehicleIdToContext("id");
  }

  @Test
  @DisplayName("should throw exception for duplicate vehicle ID")
  void shouldThrowExceptionForDuplicateVehicleId() {
    // given
    SimulationContext context = new SimulationContext();
    context.addVehicleIdToContext("id");

    // then
    assertThatThrownBy(() -> context.addVehicleIdToContext("id"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Duplicated vehicle id");
  }
}
