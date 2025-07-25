package org.example.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.example.io.parser.CommandListDTO;

@Getter
public class ValidatedCommandListDto {

  @NotEmpty @NotNull @Valid
  private List<Map<@NotNull @NotEmpty String, @NotNull @NotEmpty String>> commands;

  public CommandListDTO toCoreDto() {
    CommandListDTO dto = new CommandListDTO();
    dto.setCommands(this.commands);
    return dto;
  }
}
