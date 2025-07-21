package org.example;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.simulation.SimulationProcessor;
import org.example.simulation.command.Command;
import org.example.io.parser.CommandListDTO;
import org.example.io.parser.CommandParser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(
                "C:\\Users\\macie\\OneDrive\\Pulpit\\traffic-light-system\\traffic-light-system\\cli\\src\\main\\java\\org\\example\\commands.json");

        try {
            CommandListDTO dto = mapper.readValue(file, CommandListDTO.class);
            List<Command> commands = CommandParser.parse(dto);
            SimulationProcessor simulationProcessor = new SimulationProcessor();
            simulationProcessor.executeSimulation(commands);
        }
        catch (IOException e){
            throw new IllegalArgumentException("Invalid input file", e);
        }
    }
}