package org.example.algorithm.intersection;

import org.example.algorithm.vehicle.Vehicle;

import java.util.*;

import static org.example.algorithm.intersection.LaneType.LEFT;
import static org.example.algorithm.intersection.LaneType.STRAIGHT_OR_RIGHT;
import static org.example.util.Direction.*;

public class TrafficLightController {

    private Intersection intersection;
    private static TrafficLightController instance;

    Set<LaneKey> phase1 = Set.of(
            new LaneKey(NORTH, STRAIGHT_OR_RIGHT),
            new LaneKey(SOUTH, STRAIGHT_OR_RIGHT)
    );

    Set<LaneKey> phase2 = Set.of(
            new LaneKey(WEST, STRAIGHT_OR_RIGHT),
            new LaneKey(EAST, STRAIGHT_OR_RIGHT)
    );

    Set<LaneKey> phase3 = Set.of(
            new LaneKey(WEST, LEFT),
            new LaneKey(EAST, LEFT)
    );

    Set<LaneKey> phase4 = Set.of(
            new LaneKey(NORTH, LEFT),
            new LaneKey(SOUTH, LEFT)
    );

    private TrafficLightController(Intersection intersection){
        this.intersection = intersection;
    }

    public static TrafficLightController getInstance(){
        if (instance == null){
            instance = new TrafficLightController(Intersection.getInstance());
        }
        return instance;
    }

    public void calculateBestPhase(){
        List<Set<LaneKey>> phases = List.of(phase1, phase2, phase3, phase4);
        Optional<Set<LaneKey>> bestPhase = phases.stream()
                .max(Comparator.comparingInt(phase -> {
                    Set<Queue<Vehicle>> vehiclesToGoOnGivenPhase = intersection.getVehiclesOnGivenLanes(phase);
                    return vehiclesToGoOnGivenPhase.stream()
                            .mapToInt(Queue::size)
                            .sum();
                }));
        intersection.setLaneKeysWithGreenLight(bestPhase.get());
    }
}
