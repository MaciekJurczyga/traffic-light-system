package org.example.io.parser;

import java.util.List;
import java.util.Map;

/**
 * Class which user input file is mapped to
 */
public class CommandListDTO {

  /**
   * List of commands. Each command is a Map<String, String>.
   */
  private List<Map<String, String>> commands;

  public List<Map<String, String>> getCommands() {
    return commands;
  }

  public CommandListDTO(List<Map<String, String>> commands){
    this.commands = commands;
  }
}
