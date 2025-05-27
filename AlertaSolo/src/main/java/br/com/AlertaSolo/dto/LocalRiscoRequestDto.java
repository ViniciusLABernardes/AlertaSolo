package br.com.AlertaSolo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class LocalRiscoRequestDto {

    public LocalRiscoRequestDto() {
    }

    public LocalRiscoRequestDto(String nomeLocal, double latitude, double longitude,
                                String cidade, String uf, String grauRisco, Boolean ativo) {
        this.nomeLocal = nomeLocal;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cidade = cidade;
        this.uf = uf;
        this.grauRisco = grauRisco;
        this.ativo = ativo;
    }

    @NotBlank(message = "O nome do local é obrigatório")
    @Size(max = 450, message = "O nome pode ter no máximo 450 caracteres")
    private String nomeLocal;

    @NotNull(message = "A latitude é obrigatória")
    private Double latitude;

    @NotNull(message = "A longitude é obrigatória")
    private Double longitude;

    @NotBlank(message = "A cidade é obrigatória")
    @Size(max = 100, message = "A cidade pode ter no máximo 100 caracteres")
    private String cidade;

    @NotBlank(message = "A UF é obrigatória")
    @Size(max = 6, message = "A UF pode ter no máximo 6 caracteres")
    private String uf;

    @NotBlank(message = "O grau de risco é obrigatório")
    @Size(max = 30, message = "O grau de risco pode ter no máximo 30 caracteres")
    private String grauRisco;

    @NotNull(message = "O campo 'ativo' é obrigatório")
    private Boolean ativo;

    public String getNomeLocal() {
        return nomeLocal;
    }

    public void setNomeLocal(String nomeLocal) {
        this.nomeLocal = nomeLocal;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
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
}