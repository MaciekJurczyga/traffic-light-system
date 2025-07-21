package org.example;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.commands.Command;
import org.example.parser.CommandListDTO;
import org.example.parser.CommandParser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        File file = new File("commands.json");

        try {
            CommandListDTO dto = mapper.readValue(file, CommandListDTO.class);
            List<Command> commands = CommandParser.parse(dto);
            commands.forEach(System.out::println);
        }
        catch (IOException e){
            throw new IllegalArgumentException("Invalid input file", e);
        }

    }
}