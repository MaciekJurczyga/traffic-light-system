package org.example.service;

import org.example.dto.ValidatedCommandListDto;
import org.example.io.parser.CommandListDTO;
import org.example.io.parser.CommandParser;
import org.example.response.StepStatusesWrapper;
import org.example.simulation.command.Command;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SimulationServiceUnitTest {


    @Test
    void shouldRunSimulation() {
        // given
        SimulationService service = new SimulationService();
        ValidatedCommandListDto dto = mock(ValidatedCommandListDto.class);
        CommandListDTO coreDto = mock(CommandListDTO.class);
        when(dto.toCoreDto()).thenReturn(coreDto);

        List<Command> commands = List.of(mock(Command.class));


        try (MockedStatic<CommandParser> parserMock = mockStatic(CommandParser.class)) {
            parserMock.when(() -> CommandParser.parse(coreDto)).thenReturn(commands);

            StepStatusesWrapper result = service.runSimulation(dto);

            assertThat(result).isNotNull();

        }
    }

}