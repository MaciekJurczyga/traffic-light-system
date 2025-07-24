package org.example.simulation.intersection.strategy;

import org.example.simulation.intersection.IntersectionTrafficController;
import org.example.simulation.intersection.TrafficLightPhase;

public class TrafficLightPhaseCalculator {
  private final IntersectionTrafficController intersectionTrafficController;
  private final TrafficLightPhaseStrategy strategy;

  public TrafficLightPhaseCalculator(
      IntersectionTrafficController intersectionTrafficController,
      TrafficLightPhaseStrategy strategy) {
    this.intersectionTrafficController = intersectionTrafficController;
    this.strategy = strategy;
  }

  /**
   * Method which uses one of TrafficLightPhaseStrategy implementation to calculate best phase
   *
   * @return best TrafficLightPhase
   */
  public TrafficLightPhase calculateBestPhase() {
    return strategy.calculateBestPhase(intersectionTrafficController.getVehiclesPerLaneMap());
  }
}
