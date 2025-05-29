package br.com.AlertaSolo.dto;

import br.com.AlertaSolo.entity.LocalRisco;
import br.com.AlertaSolo.entity.Sensor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class SensorRequestDto {

    public SensorRequestDto() {
    }

    public SensorRequestDto(long idLocalRisco, String codigoEsp32, String status, String tipoSensor) {
        this.idLocalRisco = idLocalRisco;
        this.codigoEsp32 = codigoEsp32;
        this.status = status;
        this.tipoSensor = tipoSensor;

    }

    @NotNull(message = "Id do local obrigatório!")
    private long idLocalRisco;

    @NotBlank(message = "codigo do esp32 obrigatório")
    private String codigoEsp32;

    @NotBlank(message = "status é obrigatório")
    private String status;

    @NotBlank(message = "o tipo do sensor é obrigatório")
    private String tipoSensor;



    public long getIdLocalRisco() {
        return idLocalRisco;
    }

    public void setIdLocalRisco(long idLocalRisco) {
        this.idLocalRisco = idLocalRisco;
    }

    public String getCodigoEsp32() {
        return codigoEsp32;
    }

    public void setCodigoEsp32(String codigoEsp32) {
        this.codigoEsp32 = codigoEsp32;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipoSensor() {
        return tipoSensor;
    }

    public void setTipoSensor(String tipoSensor) {
        this.tipoSensor = tipoSensor;
    }


}
