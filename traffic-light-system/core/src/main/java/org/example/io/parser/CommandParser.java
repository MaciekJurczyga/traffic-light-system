package org.example.io.parser;

import org.example.simulation.command.AddVehicle;
import org.example.simulation.command.Command;
import org.example.simulation.command.Step;
import org.example.simulation.vehicle.Vehicle;

import java.util.List;
import java.util.stream.Collectors;

public class CommandParser {

    public static List<Command> parse(CommandListDTO dto) {
        return dto.getCommands().stream()
                .map(cmd -> {
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