package org.example.simulation.vehicle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.example.simulation.intersection.parameters.TunableParameters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VehicleUnitTest {

  @Test
  @DisplayName("Should not create vehicle with null or empty id")
  void shouldThrowOnCreatingVehicleWithInvalidId() {
    assertThatThrownBy(() -> new Vehicle(null, "south", "west"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("vehicleId must not be null or empty!");
  }

  @Test
  @DisplayName("Turns right method should return true if vehicle turns right")
  void shouldReturnTrueWhenVehicleTurnsRight() {
    Vehicle vehicle = new Vehicle("id", "south", "east");
    assertThat(vehicle.turnsRight()).isTrue();
  }

  @Test
  @DisplayName("Turns right method should return false if vehicle does not turn right")
  void shouldReturnFalseWhenVehicleNotTurnsRight() {
    Vehicle vehicle = new Vehicle("id", "south", "north");
    assertThat(vehicle.turnsRight()).isFalse();
  }

  @Test
  @DisplayName("Increase Waiting Time method should increase waiting time by correct value")
  void shouldIncreaseWaitingTimeByCorrectNumber() {
    Vehicle vehicle = new Vehicle("id", "south", "north");
    int originalWaitingTime = vehicle.getWaitingTime();
    assertThat(originalWaitingTime).isEqualTo(1);
    vehicle.increaseWaitingTime();
    int newWaitingTime = vehicle.getWaitingTime();

    assertThat(newWaitingTime - originalWaitingTime)
        .isEqualTo(TunableParameters.WAITING_TIME_BONUS);
  }
}
