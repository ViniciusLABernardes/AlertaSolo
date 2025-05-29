package br.com.AlertaSolo.dto;

import br.com.AlertaSolo.entity.LocalRisco;
import br.com.AlertaSolo.entity.Sensor;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class SensorResponseDto {


        public SensorResponseDto() {
        }

        public SensorResponseDto(long idSensor,LocalRisco localRisco, String codigoEsp32, String status, String tipoSensor, LocalDate dataInstalacao) {
            this.idSensor = idSensor;
            this.localRisco = localRisco;
            this.codigoEsp32 = codigoEsp32;
            this.status = status;
            this.tipoSensor = tipoSensor;
            this.dataInstalacao = dataInstalacao;
        }


        private Long idSensor;

        private LocalRisco localRisco;

        private String codigoEsp32;

        private String status;

        private String tipoSensor;

        private LocalDate dataInstalacao;


        public Long getIdSensor() {
            return idSensor;
        }

        public void setIdSensor(Long idSensor) {
            this.idSensor = idSensor;
        }

        public LocalRisco getLocalRisco() {
            return localRisco;
        }

        public void setLocalRisco(LocalRisco localRisco) {
            this.localRisco = localRisco;
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

        public LocalDate getDataInstalacao() {
            return dataInstalacao;
        }

        public void setDataInstalacao(LocalDate dataInstalacao) {
            this.dataInstalacao = dataInstalacao;
        }


}
