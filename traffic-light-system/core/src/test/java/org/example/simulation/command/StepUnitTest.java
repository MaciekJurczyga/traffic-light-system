package org.example.simulation.command;

import org.example.simulation.SimulationContext;
import org.example.simulation.intersection.IntersectionTrafficController;
import org.example.simulation.intersection.TrafficLightController;
import org.example.simulation.intersection.TrafficLightPhase;
import org.example.simulation.vehicle.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class StepUnitTest {

    private SimulationContext mockContext;
    private TrafficLightController mockLightController;
    private IntersectionTrafficController mockIntersectionController;
    private TrafficLightPhase mockPhase;
    private Vehicle mockVehicle;

    @BeforeEach
    void setUp() {
        mockContext = mock(SimulationContext.class);
        mockLightController = mock(TrafficLightController.class);
        mockIntersectionController = mock(IntersectionTrafficController.class);
        mockPhase = mock(TrafficLightPhase.class);
        mockVehicle = mock(Vehicle.class);

        when(mockContext.getTrafficLightController()).thenReturn(mockLightController);
        when(mockContext.getIntersectionTrafficController()).thenReturn(mockIntersectionController);
        when(mockLightController.updatePhase()).thenReturn(mockPhase);
        when(mockIntersectionController.moveVehicles(mockPhase)).thenReturn(List.of(mockVehicle));
        when(mockVehicle.getVehicleId()).thenReturn("some_id");
    }

    @Test
    @DisplayName("Step command should update phase and move vehicles")
    void shouldUpdatePhaseAndMoveVehicles() {
        // given
        Step stepCommand = new Step();

        // when
        stepCommand.executeCommand(mockContext);

        // then
        verify(mockContext).getTrafficLightController();
        verify(mockContext).getIntersectionTrafficController();
        verify(mockLightController).updatePhase();
        verify(mockIntersectionController).moveVehicles(mockPhase);
        verifyNoMoreInteractions(mockContext, mockLightController, mockIntersectionController);
    }
}