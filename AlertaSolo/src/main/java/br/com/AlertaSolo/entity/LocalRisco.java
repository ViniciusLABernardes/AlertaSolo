package br.com.AlertaSolo.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_local_risco")
@SequenceGenerator(sequenceName = "tb_local_seq",name = "local_seq",allocationSize = 1)
public class LocalRisco {

    public LocalRisco(){

    }


    public LocalRisco(String nomeLocal, double latitude, double longitude, String cidade, String uf, String grauRisco, Boolean ativo) {
        this.nomeLocal = nomeLocal;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cidade = cidade;
        this.uf = uf;
        this.grauRisco = grauRisco;
        this.ativo = ativo;
    }

    @Id
    @Column(name = "id_local")
    @GeneratedValue(generator = "local_seq",strategy = GenerationType.SEQUENCE)
    private long idLocal;

    @Column(name = "nome_local",nullable = false,length = 450)
    private String nomeLocal;

    @Column(name ="latitude",nullable = false)
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "cidade",nullable = false,length = 100)
    private String cidade;

    @Column(name = "uf",nullable = false,length = 6)
    private String uf;

    @Column(name = "grau_risco",nullable = false,length = 30)
    private String grauRisco;

    @Column(name = "ativo",nullable = false)
    private Boolean ativo;

    @OneToMany(mappedBy = "localRisco", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Sensor> sensores;

    public List<Sensor> getSensores() {
        return sensores;
    }

    public void setSensores(List<Sensor> sensores) {
        this.sensores = sensores;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        LocalRisco outro = (LocalRisco) obj;
        return idLocal == outro.idLocal;
    }
}
