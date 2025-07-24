package org.example.simulation.intersection.strategy;

import java.util.*;
import org.example.simulation.intersection.LaneIdentifier;
import org.example.simulation.intersection.TrafficLightPhase;
import org.example.simulation.intersection.TrafficLightPhasesHolder;
import org.example.simulation.vehicle.Vehicle;

/**
 * Simplest traffic light phase calculation algorithm. For each phase, calculates length of all
 * vehicles which could go on green light Phase with most vehicles wins
 */
public class QueueLengthBasedStrategy implements TrafficLightPhaseStrategy {

  /**
   * Simplest traffic light phase calculation algorithm. For each phase, calculates length of all
   * vehicles which could go on green light Phase with most vehicles wins
   *
   * @param laneQueues - lane identifier - queue of vehicles on that lane map
   * @return best calculated phase
   */
  @Override
  public TrafficLightPhase calculateBestPhase(Map<LaneIdentifier, Queue<Vehicle>> laneQueues) {
    return TrafficLightPhasesHolder.getAllTrafficLightPhases().stream()
        .max(
            Comparator.comparingInt(
                phase ->
                    phase.laneIdentifiers().stream()
                        .map(laneQueues::get)
                        .filter(Objects::nonNull)
                        .mapToInt(Queue::size)
                        .sum()))
        .orElseThrow();
  }
}
