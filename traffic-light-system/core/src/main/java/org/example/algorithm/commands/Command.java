package org.example.algorithm.commands;

import org.example.algorithm.intersection.SimulationContext;

public interface Command {
    void executeCommand(SimulationContext simulationContext);
}
