package br.com.AlertaSolo.controller;

import br.com.AlertaSolo.dto.SensorRequestDto;
import br.com.AlertaSolo.dto.SensorResponseDto;
import br.com.AlertaSolo.dto.VerificarRiscoRequestDto;
import br.com.AlertaSolo.dto.VerificarRiscoResponseDto;
import br.com.AlertaSolo.entity.LocalRisco;
import br.com.AlertaSolo.entity.Sensor;
import br.com.AlertaSolo.exceptions.IdNaoEncontradoException;
import br.com.AlertaSolo.services.LocalRiscoService;
import br.com.AlertaSolo.services.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensor")
@Tag(name = "Sensor", description = "API para gerenciamento de sensores")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @Autowired
    private LocalRiscoService localRiscoService;

    @Operation(
            summary = "Cadastrar novo sensor",
            description = "Cadastra um novo sensor e associa a um local de risco existente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sensor cadastrado com sucesso", content = @Content(schema = @Schema(implementation = SensorResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Local de risco não encontrado", content = @Content)
            }
    )
    @PostMapping
    public ResponseEntity<SensorResponseDto> cadastrarSensor(@Valid @RequestBody SensorRequestDto sensorRequestDto) throws IdNaoEncontradoException {
        LocalRisco localRisco = localRiscoService.visualizarDadosLocalRiscoEspecifico(sensorRequestDto.getIdLocalRisco())
                .orElseThrow(() -> new IdNaoEncontradoException("Local com id " + sensorRequestDto.getIdLocalRisco() + " não encontrado"));

        Sensor sensor = new Sensor(localRisco, sensorRequestDto.getCodigoEsp32(), sensorRequestDto.getStatus(),
                sensorRequestDto.getTipoSensor());

        Sensor sensorSalva = sensorService.salvarSensor(sensor, sensorRequestDto.getIdLocalRisco());
        return ResponseEntity.ok(new SensorResponseDto(sensorSalva.getIdSensor(), sensorSalva.getLocalRisco(),
                sensorSalva.getCodigoEsp32(), sensorRequestDto.getStatus(),
                sensorRequestDto.getTipoSensor(), sensorSalva.getDataInstalacao()));
    }

    @Operation(
            summary = "Buscar sensor por ID",
            description = "Retorna os dados de um sensor específico.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sensor encontrado", content = @Content(schema = @Schema(implementation = SensorResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Sensor não encontrado", content = @Content)
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<SensorResponseDto> lerSensorEspecifico(
            @Parameter(description = "ID do sensor", example = "1")
            @PathVariable long id) {
        return sensorService.visualizarDadosSensorEspecifico(id)
                .map(sensor -> ResponseEntity.ok(
                        new SensorResponseDto(sensor.getIdSensor(), sensor.getLocalRisco(),
                                sensor.getCodigoEsp32(), sensor.getStatus(), sensor.getTipoSensor(), sensor.getDataInstalacao()))
                )
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Verificar risco de deslizamento",
            description = "Verifica se há risco de deslizamento com base em dados fornecidos pelos sensores.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Verificação processada", content = @Content(schema = @Schema(implementation = String.class)))
            }
    )
    @PostMapping("/verificar-risco")
    public ResponseEntity<VerificarRiscoResponseDto> verificarRisco(
            @Valid @RequestBody VerificarRiscoRequestDto dto) {
        VerificarRiscoResponseDto resposta =  sensorService.verificarRiscoDeslizamento(dto.getIdLocal(), dto.getUmidade(), dto.getInclinacao(), dto.getTremor());
        return ResponseEntity.ok(resposta);
    }



}
