package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.ValidatedCommandListDto;
import org.example.response.StepStatusesWrapper;
import org.example.service.SimulationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SimulationController {

    private final SimulationService simulationService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot!";
    }

    @PostMapping("/simulation/run")
    public ResponseEntity<StepStatusesWrapper> runSimulation(@RequestBody @Valid ValidatedCommandListDto dto){
        return ResponseEntity.ok(simulationService.runSimulation(dto));
    }
}