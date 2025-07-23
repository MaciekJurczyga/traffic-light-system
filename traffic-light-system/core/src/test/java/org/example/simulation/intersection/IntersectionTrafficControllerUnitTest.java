package org.example.simulation.intersection;


import org.example.simulation.vehicle.Direction;
import org.example.simulation.vehicle.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class IntersectionTrafficControllerUnitTest {

    private IntersectionTrafficController controller;

    @BeforeEach
    void setup() {
        controller = new IntersectionTrafficController();
    }

    @Test
    @DisplayName("should initialize queues for all direction and lane type combinations")
    void shouldInitializeQueues() {
        Map<LaneIdentifier, Queue<Vehicle>> map = controller.getVehiclesPerLaneMap();

        int expectedSize = Direction.values().length * LaneType.values().length;

        assertThat(map).hasSize(expectedSize);
        map.values().forEach(queue -> assertThat(queue).isEmpty());
    }

    @Test
    @DisplayName("should add vehicle to correct lane queue based on start and end direction")
    void shouldAddVehicleToCorrectQueue() {
        // given
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getStartRoad()).thenReturn(Direction.NORTH);
        when(vehicle.getEndRoad()).thenReturn(Direction.EAST);

        // when
        controller.addVehicleToProperLane(vehicle);

        // then
        LaneIdentifier expectedLane = new LaneIdentifier(Direction.NORTH, LaneType.LEFT);
        Queue<Vehicle> queue = controller.getVehiclesPerLaneMap().get(expectedLane);

        assertThat(queue).containsExactly(vehicle);
    }

    @Test
    @DisplayName("should move first vehicle on regular green light lane")
    void shouldMoveVehicleOnRegularLight() {
        // given
        LaneIdentifier lane = new LaneIdentifier(Direction.NORTH, LaneType.LEFT);
        Vehicle vehicle = mock(Vehicle.class);
        controller.getVehiclesPerLaneMap().get(lane).add(vehicle);

        TrafficLightPhase phase = new TrafficLightPhase(
                Set.of(lane),
                Map.of(lane, TrafficLightType.REGULAR)
        );

        // when
        List<Vehicle> moved = controller.moveVehicles(phase);

        // then
        assertThat(moved).containsExactly(vehicle);
        assertThat(controller.getVehiclesPerLaneMap().get(lane)).isEmpty();
    }

    @Test
    @DisplayName("should move first vehicle if conditional and it turns right")
    void shouldMoveConditionalRightTurner() {
        // given
        LaneIdentifier lane = new LaneIdentifier(Direction.NORTH, LaneType.STRAIGHT_OR_RIGHT);
        Vehicle rightTurner = mock(Vehicle.class);
        when(rightTurner.turnsRight()).thenReturn(true);

        controller.getVehiclesPerLaneMap().get(lane).add(rightTurner);

        TrafficLightPhase phase = new TrafficLightPhase(
                Set.of(lane),
                Map.of(lane, TrafficLightType.CONDITIONAL_RIGHT)
        );

        // when
        List<Vehicle> moved = controller.moveVehicles(phase);

        // then
        assertThat(moved).containsExactly(rightTurner);
        assertThat(controller.getVehiclesPerLaneMap().get(lane)).isEmpty();
    }

    @Test
    @DisplayName("should not move vehicle on conditional light if it does not turn right")
    void shouldNotMoveConditionalIfNotRightTurner() {
        // given
        LaneIdentifier lane = new LaneIdentifier(Direction.EAST, LaneType.STRAIGHT_OR_RIGHT);
        Vehicle notRightTurner = mock(Vehicle.class);
        when(notRightTurner.turnsRight()).thenReturn(false);

        controller.getVehiclesPerLaneMap().get(lane).add(notRightTurner);

        TrafficLightPhase phase = new TrafficLightPhase(
                Set.of(lane),
                Map.of(lane, TrafficLightType.CONDITIONAL_RIGHT)
        );

        // when
        List<Vehicle> moved = controller.moveVehicles(phase);

        // then
        assertThat(moved).isEmpty();
        assertThat(controller.getVehiclesPerLaneMap().get(lane)).containsExactly(notRightTurner);
    }

    @Test
    @DisplayName("should increase waiting time of vehicles on red light")
    void shouldUpdateWaitingTimeForRedLanes() {
        // given
        LaneIdentifier redLane = new LaneIdentifier(Direction.WEST, LaneType.LEFT);
        LaneIdentifier greenLane = new LaneIdentifier(Direction.EAST, LaneType.STRAIGHT_OR_RIGHT);

        Vehicle redVehicle = mock(Vehicle.class);
        Vehicle greenVehicle = mock(Vehicle.class);

        controller.getVehiclesPerLaneMap().get(redLane).add(redVehicle);
        controller.getVehiclesPerLaneMap().get(greenLane).add(greenVehicle);

        TrafficLightPhase greenPhase = new TrafficLightPhase(
                Set.of(greenLane),
                Map.of(greenLane, TrafficLightType.REGULAR)
        );

        // when
        controller.moveVehicles(greenPhase);

        // then
        verify(redVehicle).increaseWaitingTime();
        verify(greenVehicle, never()).increaseWaitingTime();
    }
}
