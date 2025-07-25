package org.example.simulation.intersection;

import java.util.*;
import java.util.stream.Collectors;
import org.example.simulation.vehicle.Direction;
import org.example.simulation.vehicle.Vehicle;

/** Class that controls traffic state of intersection */
public class IntersectionTrafficController {

  private final Map<LaneIdentifier, Queue<Vehicle>> vehiclesPerLaneMap = new HashMap<>();

  /** Initializes each queue. One queue for every lane */
  public IntersectionTrafficController() {
    for (Direction from : Direction.values()) {
      for (LaneType laneType : LaneType.values()) {
        vehiclesPerLaneMap.put(new LaneIdentifier(from, laneType), new LinkedList<>());
      }
    }
  }

  /**
   * Add vehicle to proper lane (queue) basing on start and end road
   *
   * @param vehicle - vehicle to be added to proper queue
   */
  public void addVehicleToProperLane(Vehicle vehicle) {
    LaneType laneType = LaneType.determineLaneType(vehicle.getStartRoad(), vehicle.getEndRoad());
    vehiclesPerLaneMap.get(new LaneIdentifier(vehicle.getStartRoad(), laneType)).add(vehicle);
  }

  public Map<LaneIdentifier, Queue<Vehicle>> getVehiclesPerLaneMap() {
    return vehiclesPerLaneMap;
  }

  /**
   * Moves first vehicles of each queue that represents a lane with green light
   * Triggers increasing waiting time of vehicles with red light
   * @param currentGreenLightPhase phase representing each lane with green light
   * @return list of moved vehicles
   */
  public List<Vehicle> moveVehicles(TrafficLightPhase currentGreenLightPhase) {
    Set<LaneIdentifier> regularLaneIdentifiers =
        currentGreenLightPhase.laneIdentifiers().stream()
            .filter(currentGreenLightPhase::isLaneRegular)
            .collect(Collectors.toSet());
    updateWaitingTimeOfVehiclesWithRedLight(regularLaneIdentifiers);
    List<Vehicle> moved = new ArrayList<>();

    for (LaneIdentifier lane : currentGreenLightPhase.laneIdentifiers()) {
      Queue<Vehicle> queue = vehiclesPerLaneMap.get(lane);
      if (queue == null || queue.isEmpty()) {
        continue;
      }

      if (currentGreenLightPhase.isLaneRegular(lane)) {
        moved.add(queue.poll());
      } else if (currentGreenLightPhase.isLaneConditional(lane)
          && queue.peek() != null
          && queue.peek().turnsRight()) {
        moved.add(queue.poll());
      }
    }

    return moved;
  }

  /**
   * For each vehicle of each queue updates waiting time
   *
   * @param lanesWithGreenLight - lanes with green light, vehicles on those lanes won't have
   *     increased waiting time
   */
  private void updateWaitingTimeOfVehiclesWithRedLight(Set<LaneIdentifier> lanesWithGreenLight) {
    vehiclesPerLaneMap.entrySet().stream()
        .filter(entry -> !lanesWithGreenLight.contains(entry.getKey()))
        .map(Map.Entry::getValue)
        .flatMap(Queue::stream)
        .forEach(Vehicle::increaseWaitingTime);
  }
}
