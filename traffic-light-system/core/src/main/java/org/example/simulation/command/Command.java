package org.example.simulation.command;

import org.example.simulation.SimulationContext;

public interface Command {
  void executeCommand(SimulationContext simulationContext);
}
