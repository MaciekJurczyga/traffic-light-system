package org.example.simulation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.example.simulation.command.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SimulationProcessorUnitTest {

  @Test
  @DisplayName("should execute all commands using SimulationContext")
  void shouldExecuteAllCommands() {
    // given
    Command command1 = mock(Command.class);
    Command command2 = mock(Command.class);

    List<Command> commands = List.of(command1, command2);
    SimulationProcessor processor = new SimulationProcessor();

    // when
    processor.executeSimulation(commands);

    // then
    verify(command1).executeCommand(any(SimulationContext.class));
    verify(command2).executeCommand(any(SimulationContext.class));
  }
}
