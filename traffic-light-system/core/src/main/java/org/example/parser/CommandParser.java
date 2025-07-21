package org.example.parser;

import org.example.commands.AddVehicle;
import org.example.commands.Command;
import org.example.commands.Step;
import org.example.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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