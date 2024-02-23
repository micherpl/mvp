package com.energy.solution.mvp.controllers;

import com.energy.solution.mvp.api.DeviceConfigurationDto;
import com.energy.solution.mvp.api.GetResponseConfigurationDto;
import com.energy.solution.mvp.api.ResponseConfigurationDto;
import com.energy.solution.mvp.services.ConfigurationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/configurations")
public class ConfigurationController {

    private final ConfigurationService configurationService;

    @Operation(summary = "Pobierz konfigurację", responses = {
            @ApiResponse(responseCode = "200", description = "Konfiguracja znaleziona",
                    content = @Content(schema = @Schema(implementation = GetResponseConfigurationDto.class))),
            @ApiResponse(responseCode = "404", description = "Konfiguracja nie znaleziona")})
    @GetMapping("/{configurationId}")
    public ResponseEntity<GetResponseConfigurationDto> getConfiguration(@PathVariable("configurationId") Long configurationId) {
        Optional<GetResponseConfigurationDto> getConfigurationDto = configurationService.getConfiguration(configurationId);
        return getConfigurationDto.map(configurationDto -> ResponseEntity
                .status(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body(configurationDto)).orElseGet(() -> ResponseEntity.notFound()
                .build());
    }

    @Operation(summary = "Utwórz nową konfigurację", responses = {
            @ApiResponse(responseCode = "200", description = "Konfiguracja utworzona",
                    content = @Content(schema = @Schema(implementation = ResponseConfigurationDto.class)))})
    @PostMapping()
    public ResponseEntity<ResponseConfigurationDto> createConfiguration(@RequestBody DeviceConfigurationDto configuration) {
        ResponseConfigurationDto responseConfigurationDto = configurationService.createConfiguration(configuration);
        return ResponseEntity
                .status(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseConfigurationDto);
    }

    @Operation(summary = "Zaktualizuj istniejącą konfigurację", responses = {
            @ApiResponse(responseCode = "200", description = "Konfiguracja zaktualizowana",
                    content = @Content(schema = @Schema(implementation = ResponseConfigurationDto.class))),
            @ApiResponse(responseCode = "404", description = "Konfiguracja nie znaleziona")})
    @PutMapping("/{configurationId}")
    public ResponseEntity<ResponseConfigurationDto> updateConfiguration(@RequestBody DeviceConfigurationDto configuration,
                                                                        @PathVariable("configurationId") Long configurationId) {
        Optional<ResponseConfigurationDto> responseConfigurationDto = configurationService.updateConfiguration(configuration, configurationId);
        return responseConfigurationDto.map(configurationDto -> ResponseEntity
                .status(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body(configurationDto)).orElseGet(() -> ResponseEntity.notFound()
                .build());
    }

    @Operation(summary = "Usuń konfigurację", responses = {
            @ApiResponse(responseCode = "200", description = "Konfiguracja usunięta")})
    @DeleteMapping("/{configurationId}")
    public ResponseEntity<String> deleteConfiguration(@PathVariable("configurationId") Long configurationId) {
        configurationService.deleteConfiguration(configurationId);
        return ResponseEntity.ok("Konfiguracja została usunięta");
    }
}
