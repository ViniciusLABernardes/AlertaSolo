package br.com.AlertaSolo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_usuario")
@SequenceGenerator(name = "usuario_seq",allocationSize = 1,sequenceName = "tb_usuario_seq")

public class Usuario {
    public Usuario(){

    }

    public Usuario(String nome, String cpf, Integer idade, String cidade, String uf, LocalDate dataCadastro) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.cidade = cidade;
        this.uf = uf;
        this.dataCadastro = dataCadastro;
    }

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    private long idUsuario;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, length = 15)
    private String cpf;

    @Column(nullable = false)
    private Integer idade;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false, length = 2)
    private String uf;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Login login;

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
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

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    @Override
    public String toString(){
        return "id usuario: " + getIdUsuario() + ", nome: " + getNome() + ", cpf: " + getCpf() +
                ", idade: " + getIdade() + ", cidade: " + getCidade() + ", uf: " + getUf();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Usuario outro = (Usuario) obj;

        return this.idUsuario == outro.idUsuario;
    }
}



