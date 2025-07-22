package org.example.simulation.intersection;

import org.example.simulation.vehicle.Vehicle;
import org.example.simulation.vehicle.Direction;

import java.util.*;

public class IntersectionTrafficController {

    private final Map<LaneIdentifier, Queue<Vehicle>> vehiclesPerLaneMap = new HashMap<>();

    public IntersectionTrafficController(){
        for(Direction from : Direction.values()){
            for(LaneType laneType : LaneType.values()){
                vehiclesPerLaneMap.put(new LaneIdentifier(from, laneType), new LinkedList<>());
            }
        }
    }

    public void addVehicleToProperLane(Vehicle vehicle){
        LaneType laneType = LaneType.determineLaneType(vehicle.getStartRoad(), vehicle.getEndRoad());
        vehiclesPerLaneMap.get(new LaneIdentifier(vehicle.getStartRoad(), laneType)).add(vehicle);
    }

    public Map<LaneIdentifier, Queue<Vehicle>> getVehiclesPerLaneMap(){
        return vehiclesPerLaneMap;
    }

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
