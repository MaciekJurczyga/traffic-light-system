package org.example.simulation;

import java.util.ArrayList;
import java.util.List;
import org.example.response.StepStatus;
import org.example.simulation.intersection.IntersectionTrafficController;
import org.example.simulation.intersection.TrafficLightController;
import org.example.simulation.intersection.strategy.IntelligentTrafficLoadBasedStrategy;
import org.example.simulation.intersection.strategy.TrafficLightPhaseCalculator;

/** Class holding simulation context, common for each command of one simulation */
public class SimulationContext {

  private final List<String> vehiclesId = new ArrayList<>();
  private final List<StepStatus> stepStatuses = new ArrayList<>();
  private final IntersectionTrafficController intersectionTrafficController;
  private final TrafficLightController trafficLightController;

  /**
   * Simulation context is created at the beginning of each simulation
   * Initializes Simulation Controllers commonly used by all commands.
   */
  public SimulationContext() {
    this.intersectionTrafficController = new IntersectionTrafficController();
    TrafficLightPhaseCalculator calculator =
        new TrafficLightPhaseCalculator(
            intersectionTrafficController, new IntelligentTrafficLoadBasedStrategy());
    this.trafficLightController = new TrafficLightController(calculator);
  }

  public IntersectionTrafficController getIntersectionTrafficController() {
    return intersectionTrafficController;
  }

  public TrafficLightController getTrafficLightController() {
    return trafficLightController;
  }


  // Tracks all vehicleIds in given simulation, throws on duplicated vehicleId
  public void addVehicleIdToContext(String vehicleId) {
    if (vehiclesId.contains(vehicleId)) {
      throw new IllegalArgumentException("Duplicated vehicle id: " + vehicleId);
    }
    vehiclesId.add(vehicleId);
  }

  // Adds stepStatus to simulation context to build stepStatuses list. For building simulation output.
  public void addStepStatus(StepStatus status) {
    stepStatuses.add(status);
  }

  public List<StepStatus> getStepStatuses() {
    return stepStatuses;
  }
}
