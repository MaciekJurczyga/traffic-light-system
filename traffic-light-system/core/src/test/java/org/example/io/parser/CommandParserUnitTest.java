package org.example.io.parser;

import static org.assertj.core.api.Assertions.*;

import org.example.simulation.command.AddVehicle;
import org.example.simulation.command.Command;
import org.example.simulation.command.Step;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CommandParserUnitTest {

    @Test
    void parse_nullDto_throwsException() {
        assertThatThrownBy(() -> CommandParser.parse(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Command list must not be null");
    }

    @Test
    void parse_nullCommands_throwsException() {
        CommandListDTO dto = new CommandListDTO();
        dto.setCommands(null);

        assertThatThrownBy(() -> CommandParser.parse(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Command list must not be null");
    }

    @Test
    void parse_commandWithoutType_throwsException() {
        Map<String, String> cmd = new HashMap<>();
        cmd.put("vehicleId", "v1");
        CommandListDTO dto = new CommandListDTO();
        dto.setCommands(Collections.singletonList(cmd));

        assertThatThrownBy(() -> CommandParser.parse(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Type is required for each command!");
    }

    @Test
    void parse_unknownCommandType_throwsException() {
        Map<String, String> cmd = new HashMap<>();
        cmd.put("type", "unknownType");
        CommandListDTO dto = new CommandListDTO();
        dto.setCommands(Collections.singletonList(cmd));

        assertThatThrownBy(() -> CommandParser.parse(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unknown command type");
    }

    @Test
    void parse_addVehicleCommand_returnsAddVehicleCommand() {
        Map<String, String> cmd = new HashMap<>();
        cmd.put("type", "addVehicle");
        cmd.put("vehicleId", "v1");
        cmd.put("startRoad", "south");
        cmd.put("endRoad", "north");
        CommandListDTO dto = new CommandListDTO();
        dto.setCommands(Collections.singletonList(cmd));

        List<Command> result = CommandParser.parse(dto);

        assertThat(result)
                .hasSize(1)
                .first()
                .isInstanceOf(AddVehicle.class);
    }

    @Test
    void parse_stepCommand_returnsStepCommand() {
        Map<String, String> cmd = new HashMap<>();
        cmd.put("type", "step");
        CommandListDTO dto = new CommandListDTO();
        dto.setCommands(Collections.singletonList(cmd));

        List<Command> result = CommandParser.parse(dto);

        assertThat(result)
                .hasSize(1)
                .first()
                .isInstanceOf(Step.class);
    }

    @Test
    void parse_multipleCommands_returnsAllCommands() {
        Map<String, String> addVehicleCmd = new HashMap<>();
        addVehicleCmd.put("type", "addVehicle");
        addVehicleCmd.put("vehicleId", "v1");
        addVehicleCmd.put("startRoad", "south");
        addVehicleCmd.put("endRoad", "north");

        Map<String, String> stepCmd = new HashMap<>();
        stepCmd.put("type", "step");

        CommandListDTO dto = new CommandListDTO();
        dto.setCommands(Arrays.asList(addVehicleCmd, stepCmd));

        List<Command> result = CommandParser.parse(dto);

        assertThat(result)
                .hasSize(2);

    }
}
