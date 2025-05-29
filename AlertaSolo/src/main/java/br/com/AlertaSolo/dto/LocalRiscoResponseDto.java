package br.com.AlertaSolo.dto;

import br.com.AlertaSolo.entity.Sensor;
import java.util.List;

public class LocalRiscoResponseDto {

    public LocalRiscoResponseDto() {
    }

    public LocalRiscoResponseDto(long idLocal, String nomeLocal, double latitude, double longitude, String cidade,
                                 String uf, String grauRisco, Boolean ativo) {
        this.idLocal = idLocal;
        this.nomeLocal = nomeLocal;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cidade = cidade;
        this.uf = uf;
        this.grauRisco = grauRisco;
        this.ativo = ativo;
    }

    public LocalRiscoResponseDto(long idLocal, String nomeLocal, double latitude, double longitude, String cidade,
                                 String uf, String grauRisco, Boolean ativo,List<Sensor> sensores) {
        this.idLocal = idLocal;
        this.nomeLocal = nomeLocal;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cidade = cidade;
        this.uf = uf;
        this.grauRisco = grauRisco;
        this.ativo = ativo;
        this.sensores = sensores;
    }

    private long idLocal;

    private String nomeLocal;

    private double latitude;

    private double longitude;

    private String cidade;

    private String uf;

    private String grauRisco;

    private Boolean ativo;

    private List<Sensor> sensores;

    public long getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(long idLocal) {
        this.idLocal = idLocal;
    }

    public String getNomeLocal() {
        return nomeLocal;
    }

    public void setNomeLocal(String nomeLocal) {
        this.nomeLocal = nomeLocal;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getGrauRisco() {
        return grauRisco;
    }

    public void setGrauRisco(String grauRisco) {
        this.grauRisco = grauRisco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<Sensor> getSensores() {
        return sensores;
    }

    public void setSensores(List<Sensor> sensores) {
        this.sensores = sensores;
    }
}
