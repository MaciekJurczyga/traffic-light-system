package org.example.simulation.command;

import java.util.List;
import org.example.response.StepStatus;
import org.example.simulation.SimulationContext;
import org.example.simulation.intersection.IntersectionTrafficController;
import org.example.simulation.intersection.TrafficLightController;
import org.example.simulation.intersection.TrafficLightPhase;
import org.example.simulation.vehicle.Vehicle;

public class Step implements Command {
  /**
   * Step implementation of command execution Triggers calculation of new phase and then moves
   * vehicles which have green light After step is completed, adds moved vehicle to the context
   *
   * @param simulationContext context of simulation, hold traffic load and traffic light controllers
   */
  @Override
  public void executeCommand(SimulationContext simulationContext) {
    // Getting controllers (state) of current simulation)
    TrafficLightController trafficLightController = simulationContext.getTrafficLightController();
    IntersectionTrafficController intersectionTrafficController =
        simulationContext.getIntersectionTrafficController();

    // Updating current Traffic Light Phase
    TrafficLightPhase currentGreenLightPhase = trafficLightController.updatePhase();

    // Moving vehicles basing on calculated Traffic Light Phase
    List<Vehicle> moved = intersectionTrafficController.moveVehicles(currentGreenLightPhase);
    simulationContext.addStepStatus(StepStatus.from(moved));
  }
}
