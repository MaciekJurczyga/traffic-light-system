package org.example;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.io.parser.CommandListDTO;
import org.example.io.parser.CommandParser;
import org.example.response.StepStatus;
import org.example.response.StepStatusesWrapper;
import org.example.simulation.SimulationProcessor;
import org.example.simulation.command.Command;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        validateArguments(args);

        String inputFilePath = args[0];
        String outputFilePath = args[1];

        try {
            StepStatusesWrapper result = runSimulation(inputFilePath);
            writeResultToFile(result, outputFilePath);
            print(result);
        } catch (IOException e) {
            throw new RuntimeException("Error processing files", e);
        }
    }

    private static void validateArguments(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Required arguments: <inputFilePath> <outputFilePath>");
        }
    }

    private static StepStatusesWrapper runSimulation(String inputFilePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File inputFile = new File(inputFilePath);

        CommandListDTO dto = mapper.readValue(inputFile, CommandListDTO.class);
        List<Command> commands = CommandParser.parse(dto);

        SimulationProcessor simulationProcessor = new SimulationProcessor();
        return simulationProcessor.executeSimulation(commands);
    }

    private static void writeResultToFile(StepStatusesWrapper wrapper, String outputFilePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File outputFile = new File(outputFilePath);
        mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, wrapper);
    }

    public static void print(StepStatusesWrapper wrapper) {
        wrapper.getStepStatuses().stream()
                .map(StepStatus::toString)
                .forEach(System.out::println);
    }
}
