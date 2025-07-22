package org.example.simulation.intersection;

import org.example.simulation.vehicle.Vehicle;
import org.example.simulation.vehicle.Direction;

import java.util.*;

/**
 * Class that controls traffic state of intersection
 */
public class IntersectionTrafficController {

    private final Map<LaneIdentifier, Queue<Vehicle>> vehiclesPerLaneMap = new HashMap<>();

    /**
     * Initializes each queue. One queue for every lane
     */
    public IntersectionTrafficController(){
        for(Direction from : Direction.values()){
            for(LaneType laneType : LaneType.values()){
                vehiclesPerLaneMap.put(new LaneIdentifier(from, laneType), new LinkedList<>());
            }
        }
    }

    /**
     * Add vehicle to proper lane (queue) basing on start and end road
     * @param vehicle - vehicle to be added to proper queue
     */
    public void addVehicleToProperLane(Vehicle vehicle){
        LaneType laneType = LaneType.determineLaneType(vehicle.getStartRoad(), vehicle.getEndRoad());
        vehiclesPerLaneMap.get(new LaneIdentifier(vehicle.getStartRoad(), laneType)).add(vehicle);
    }

    public Map<LaneIdentifier, Queue<Vehicle>> getVehiclesPerLaneMap(){
        return vehiclesPerLaneMap;
    }

    /**
     * Moves first vehicles of each queue that represents a lane with green light
     * @param currentGreenLightPhase phase representing each lane with green light
     * @return list of moved vehicles
     */
    public List<Vehicle> moveVehicles(TrafficLightPhase currentGreenLightPhase) {
        updateWaitingTimeOfVehiclesWithRedLight(currentGreenLightPhase);
        List<Vehicle> moved = new ArrayList<>();
        for (LaneIdentifier lane : currentGreenLightPhase.laneIdentifiers()) {
            Queue<Vehicle> queue = vehiclesPerLaneMap.get(lane);
            if (queue != null && !queue.isEmpty()) {
                moved.add(queue.poll());
            }
        }
        return moved;
    }

    /**
     * For each vehicle of each queue updates waiting time
     * @param currentGreenLightPhase - updates vehicles of each lines except of lines represented by this param
     */
    private void updateWaitingTimeOfVehiclesWithRedLight(TrafficLightPhase currentGreenLightPhase){
        TrafficLightPhasesHolder.getAllTrafficLightPhaseExcept(currentGreenLightPhase)
                .stream()
                .flatMap(phase -> phase.laneIdentifiers().stream())
                .forEach(laneIdentifier -> {
                    Queue<Vehicle> vehicles = vehiclesPerLaneMap.get(laneIdentifier);
                    if (vehicles != null && !vehicles.isEmpty()) {
                        vehicles.forEach(Vehicle::increaseWaitingTime);
                    }
                });
    }

}
