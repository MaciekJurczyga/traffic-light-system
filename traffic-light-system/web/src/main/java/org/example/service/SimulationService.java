package org.example.service;

import java.util.List;
import org.example.dto.ValidatedCommandListDto;
import org.example.io.parser.CommandParser;
import org.example.response.StepStatusesWrapper;
import org.example.simulation.SimulationProcessor;
import org.example.simulation.command.Command;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {

  public StepStatusesWrapper runSimulation(ValidatedCommandListDto commandListDTO) {
    SimulationProcessor processor = new SimulationProcessor();
    List<Command> commands = CommandParser.parse(commandListDTO.toCoreDto());
    return processor.executeSimulation(commands);
  }
}
