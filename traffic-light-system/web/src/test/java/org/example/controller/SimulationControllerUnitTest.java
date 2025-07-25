package org.example.controller;

import org.example.dto.ValidatedCommandListDto;
import org.example.response.StepStatus;
import org.example.response.StepStatusesWrapper;
import org.example.service.SimulationService;
import org.example.simulation.vehicle.Vehicle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SimulationControllerUnitTest {

    private AutoCloseable autoCloseable;

    @Mock
    private SimulationService simulationService;

    @InjectMocks
    private SimulationController simulationController;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void runSimulation_ShouldReturnStepStatusesWrapper() {
        // given
        ValidatedCommandListDto dto = new ValidatedCommandListDto();
        StepStatusesWrapper expectedWrapper = StepStatusesWrapper.from(
                List.of(
                        StepStatus.from(
                                List.of(new Vehicle("id", "south", "north"))))
        );
        when(simulationService.runSimulation(dto)).thenReturn(expectedWrapper);

        // when
        ResponseEntity<StepStatusesWrapper> response = simulationController.runSimulation(dto);

        // then
        assertThat(response)
                .isNotNull()
                .extracting(ResponseEntity::getStatusCodeValue)
                .isEqualTo(200);

        assertThat(response.getBody())
                .isEqualTo(expectedWrapper);

        verify(simulationService, times(1)).runSimulation(dto);
    }

}
