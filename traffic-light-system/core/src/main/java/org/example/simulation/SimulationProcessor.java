package org.example.simulation;

import org.example.simulation.command.Command;

import java.util.List;

public class SimulationProcessor {

    public void executeSimulation(List<Command> commands){
        SimulationContext context = new SimulationContext();
        commands.forEach(command -> command.executeCommand(context));
    }
}
