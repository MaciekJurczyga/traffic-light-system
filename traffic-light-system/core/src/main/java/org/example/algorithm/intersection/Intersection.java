package org.example.algorithm.intersection;

import org.example.algorithm.vehicle.Vehicle;
import org.example.util.Direction;

import java.util.*;
import java.util.stream.Collectors;

public class Intersection {

    private final Map<LaneKey, Queue<Vehicle>> laneQueues = new HashMap<>();
    private Set<LaneKey> laneKeysWithGreenLight;
    private static Intersection instance;
    private int counter = 1;

    public static Intersection getInstance(){
        if (instance == null){
            instance = new Intersection();
        }
        return instance;
    }

    private Intersection(){
        for(Direction from : Direction.values()){
            for(LaneType laneType : LaneType.values()){
                laneQueues.put(new LaneKey(from, laneType), new LinkedList<>());
            }
        }
    }

    public void addVehicleToQueue(Vehicle vehicle){
        LaneType laneType = LaneType.determineLaneType(vehicle.getStartRoad(), vehicle.getEndRoad());
        laneQueues.get(new LaneKey(vehicle.getStartRoad(), laneType)).add(vehicle);
    }

    public void setLaneKeysWithGreenLight(Set<LaneKey> laneKeysWithGreenLight){
        this.laneKeysWithGreenLight = new HashSet<>(laneKeysWithGreenLight);
    }

    public Set<Queue<Vehicle>> getVehiclesOnGivenLanes(Set<LaneKey> laneKeys){
        return laneKeys.stream().map(laneQueues::get).collect(Collectors.toSet());
    }

    public void moveVehiclesWithGreenLight(){
        System.out.println("---------------------------------------");
        System.out.println("step number: " + counter++);
        laneKeysWithGreenLight.forEach(
                laneKey -> {
                    Queue<Vehicle> vehicles = laneQueues.get(laneKey);
                    Vehicle vehicle = vehicles.poll();
                    if(vehicle == null){
                        System.out.println("--");
                    }
                    else{
                        System.out.println(vehicle.getVehicleId());
                    }
                }
        );
        System.out.println("---------------------------------------");
    }
}
