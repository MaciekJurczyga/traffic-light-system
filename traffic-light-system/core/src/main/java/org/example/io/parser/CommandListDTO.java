package org.example.io.parser;

import java.util.List;
import java.util.Map;

public class CommandListDTO {

  private List<Map<String, String>> commands;

  public List<Map<String, String>> getCommands() {
    return commands;
  }

  public void setCommands(List<Map<String, String>> commands) {
    this.commands = commands;
  }
}
