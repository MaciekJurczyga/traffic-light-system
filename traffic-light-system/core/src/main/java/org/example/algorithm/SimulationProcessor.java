package org.example.algorithm;

import org.example.algorithm.commands.Command;
import org.example.algorithm.intersection.SimulationContext;

import java.util.List;

public class SimulationProcessor {

    public void executeSimulation(List<Command> commands){
        SimulationContext context = new SimulationContext();
        commands.forEach(command -> command.executeCommand(context));
    }
}
