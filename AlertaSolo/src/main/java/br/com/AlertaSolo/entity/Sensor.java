package br.com.AlertaSolo.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_sensor")
@SequenceGenerator(name = "sensor_seq",sequenceName = "tb_sensor_seq",allocationSize = 1)
public class Sensor {

    public Sensor() {
    }

    public Sensor(LocalRisco localRisco, String codigoEsp32, String status, String tipoSensor) {
        this.localRisco = localRisco;
        this.codigoEsp32 = codigoEsp32;
        this.status = status;
        this.tipoSensor = tipoSensor;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sensor_seq")
    @Column(name = "id_sensor")
    private long idSensor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_local", nullable = false)
    @JsonBackReference
    private LocalRisco localRisco;

    @Column(name = "codigo_esp32", nullable = false, unique = true)
    private String codigoEsp32;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "tipo_sensor", nullable = false)
    private String tipoSensor;

    @Column(name = "data_instalacao")
    private LocalDate dataInstalacao;

    @Column(name = "qntd_alertas",nullable = false)
    private int qntdAlerta = 0;

    public int getQntdAlerta() {
        return qntdAlerta;
    }

    public void setQntdAlerta(int qntdAlerta) {
        this.qntdAlerta = qntdAlerta;
    }

    public long getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(long idSensor) {
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
