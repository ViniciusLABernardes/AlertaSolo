package br.com.AlertaSolo.controller;


import br.com.AlertaSolo.dto.SensorRequestDto;
import br.com.AlertaSolo.dto.SensorResponseDto;
import br.com.AlertaSolo.dto.VerificarRiscoRequestDto;
import br.com.AlertaSolo.entity.LocalRisco;
import br.com.AlertaSolo.entity.Sensor;
import br.com.AlertaSolo.exceptions.IdNaoEncontradoException;
import br.com.AlertaSolo.services.LocalRiscoService;
import br.com.AlertaSolo.services.SensorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensor")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @Autowired
    private LocalRiscoService localRiscoService;

    @PostMapping
    public ResponseEntity<SensorResponseDto> cadastrarSensor(@Valid @RequestBody SensorRequestDto sensorRequestDto) throws IdNaoEncontradoException {
          LocalRisco localRisco = localRiscoService.visualizarDadosLocalRiscoEspecifico(sensorRequestDto.getIdLocalRisco())
                  .orElseThrow(() -> new IdNaoEncontradoException("Local com id " + sensorRequestDto.getIdLocalRisco() + " não encontrado"));

        Sensor sensor = new Sensor(localRisco, sensorRequestDto.getCodigoEsp32(), sensorRequestDto.getStatus(),
                sensorRequestDto.getTipoSensor());

        Sensor sensorSalva = sensorService.salvarSensor(sensor, sensorRequestDto.getIdLocalRisco());
        return ResponseEntity.ok(new SensorResponseDto(sensorSalva.getIdSensor(),sensorSalva.getLocalRisco(),
                sensorSalva.getCodigoEsp32(),sensorRequestDto.getStatus(),
                sensorRequestDto.getTipoSensor(),sensor.getDataInstalacao()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorResponseDto> lerSensorEspecifico(@PathVariable long id){
        return sensorService.visualizarDadosSensorEspecifico(id)
                .map(sensor -> ResponseEntity.ok(
                        new SensorResponseDto(sensor.getIdSensor(),sensor.getLocalRisco(),
                                sensor.getCodigoEsp32(),sensor.getStatus(),sensor.getTipoSensor(),sensor.getDataInstalacao()))
                )
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/verificar-risco")
    public ResponseEntity<String> verificarRisco(@Valid @RequestBody VerificarRiscoRequestDto dto) {
        sensorService.verificarRiscoDeslizamento(dto.getIdLocal(), dto.getUmidade(), dto.getInclinacao(), dto.getTremor());
        return ResponseEntity.ok("Verificação de risco processada.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SensorResponseDto> deletarSensor(@PathVariable long id){
        if(sensorService.visualizarDadosSensorEspecifico(id).isPresent()){
            try {
                sensorService.removerSensor(id);
                return ResponseEntity.noContent().build();
            } catch (IdNaoEncontradoException e) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
