package br.com.AlertaSolo.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class UsuarioRequestDto {

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 200, message = "O nome pode ter no máximo 200 caracteres")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;

    @NotNull(message = "A idade é obrigatória")
    @Min(value = 0, message = "A idade não pode ser negativa")
    @Max(value = 120, message = "A idade não pode ser maior que 120")
    private Integer idade;

    @NotBlank(message = "A cidade é obrigatória")
    @Size(max = 100, message = "A cidade pode ter no máximo 100 caracteres")
    private String cidade;

    @NotBlank(message = "A UF é obrigatória")
    @Size(min = 2, max = 6, message = "A UF deve ter entre 2 e 6 caracteres")
    private String uf;



    public UsuarioRequestDto() {
    }

    public UsuarioRequestDto(String nome, String cpf, Integer idade, String cidade, String uf) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.cidade = cidade;
        this.uf = uf;

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


}