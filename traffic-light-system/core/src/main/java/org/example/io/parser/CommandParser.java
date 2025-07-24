package org.example.io.parser;

import org.example.simulation.command.AddVehicle;
import org.example.simulation.command.Command;
import org.example.simulation.command.Step;
import org.example.simulation.vehicle.Vehicle;

import java.util.List;
import java.util.stream.Collectors;

public class CommandParser {
    /**
     * Parses each command to appropriate class
     * @param dto list of commands to be parsed
     * @return List of both addVehicle and step commands held together by common interface
     */
    public static List<Command> parse(CommandListDTO dto) {
        if(dto == null || dto.getCommands() == null){
            throw new IllegalArgumentException("Command list must not be null");
        }
        return dto.getCommands().stream()
                .map(cmd -> {
                    if (!cmd.containsKey("type")) {
                        throw new IllegalArgumentException("Type is required for each command!");
                    }

                    String type = cmd.get("type");
                    return switch (type) {
                        case "addVehicle" -> {
                            Vehicle vehicle = new Vehicle(
                                     cmd.get("vehicleId"),
                                     cmd.get("startRoad"),
                                     cmd.get("endRoad")
                            );
                            yield new AddVehicle(vehicle);
                        }
                        case "step" -> new Step();
                        default -> throw new IllegalArgumentException("Unknown command type: " + type);
                    };
                })
                .collect(Collectors.toList());

    }
}