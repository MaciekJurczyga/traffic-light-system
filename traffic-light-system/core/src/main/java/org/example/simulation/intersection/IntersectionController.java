package org.example.simulation.intersection;

import org.example.simulation.vehicle.Vehicle;
import org.example.simulation.vehicle.Direction;

import java.util.*;

public class IntersectionController {

    private final Map<LaneIdentifier, Queue<Vehicle>> vehiclesPerLaneMap = new HashMap<>();

    public IntersectionController(){
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
        List<Vehicle> moved = new ArrayList<>();
        for (LaneIdentifier lane : currentGreenLightPhase.laneIdentifiers()) {
            Queue<Vehicle> queue = vehiclesPerLaneMap.get(lane);
            if (queue != null && !queue.isEmpty()) {
                moved.add(queue.poll());
            }
        }
        return moved;
    }
}
