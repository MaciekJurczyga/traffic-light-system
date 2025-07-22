package org.example.simulation;

import org.example.simulation.command.Command;

import java.util.List;

public class SimulationProcessor {

    /**
     * Executes simulation by executing each command
     * @param commands - commands to be executed
     */
    public void executeSimulation(List<Command> commands){
        SimulationContext context = new SimulationContext();
        commands.forEach(command -> command.executeCommand(context));
    }
}
