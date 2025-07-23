package org.example.simulation.command;

import org.example.simulation.SimulationContext;
import org.example.simulation.intersection.IntersectionTrafficController;
import org.example.simulation.vehicle.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class AddVehicleUnitTest {

    private Vehicle mockVehicle;
    private SimulationContext mockSimulationContext;
    private IntersectionTrafficController mockTrafficController;

    @BeforeEach
    void setUp() {
        mockVehicle = mock(Vehicle.class);
        mockSimulationContext = mock(SimulationContext.class);
        mockTrafficController = mock(IntersectionTrafficController.class);

        when(mockSimulationContext.getIntersectionTrafficController()).thenReturn(mockTrafficController);
        when(mockVehicle.getVehicleId()).thenReturn("some_id");
    }

    @Test
    @DisplayName("Add vehicle command should add vehicle to context and to proper lane on intersection")
    void shouldAddVehicleToContextAndProperLane() {
        // given
        AddVehicle addVehicleCommand = new AddVehicle(mockVehicle);

        // when
        addVehicleCommand.executeCommand(mockSimulationContext);

        // then
        verify(mockSimulationContext).addVehicleIdToContext("some_id");
        verify(mockSimulationContext).getIntersectionTrafficController();
        verify(mockTrafficController).addVehicleToProperLane(mockVehicle);
        verifyNoMoreInteractions(mockSimulationContext, mockTrafficController);
    }
}