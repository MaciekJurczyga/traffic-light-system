package org.example.algorithm;

import org.example.algorithm.commands.Command;

import java.util.List;

public class SimulationProcessor {

    public SimulationProcessor(){
    }

    public void executeSimulation(List<Command> commands){
        commands.forEach(Command::executeCommand);
    }
}
