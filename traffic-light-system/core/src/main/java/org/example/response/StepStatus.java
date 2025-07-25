package org.example.response;

import java.util.List;
import org.example.simulation.vehicle.Vehicle;

/**
 * Class which holds status of each step
 */
public class StepStatus {

  /**
   * Vehicles that left intersection on given step
   */
  private List<String> leftVehicles;

  private StepStatus(List<String> leftVehicles) {
    this.leftVehicles = leftVehicles;
  }

  public static StepStatus from(List<Vehicle> vehicles) {
    List<String> ids = vehicles.stream().map(Vehicle::getVehicleId).toList();
    return new StepStatus(ids);
  }

  public List<String> getLeftVehicles(){
    return leftVehicles;
  }

  @Override
  public String toString() {
    return "StepStatus{leftVehicles=" + leftVehicles + "}";
  }
}
