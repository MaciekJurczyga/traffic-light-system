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
                    String type = (String) cmd.get("type");
                    return switch (type) {
                        case "addVehicle" -> {
                            Vehicle vehicle = new Vehicle(
                                    (String) cmd.get("vehicleId"),
                                    (String) cmd.get("startRoad"),
                                    (String) cmd.get("endRoad")
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