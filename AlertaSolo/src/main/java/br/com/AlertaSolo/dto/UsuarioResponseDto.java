package br.com.AlertaSolo.dto;

import java.time.LocalDate;

public class UsuarioResponseDto {

        private Long id;

        private String nome;

        private String cpf;

        private Integer idade;

        private String cidade;

        private String uf;

        private LocalDate dataCadastro;

        private String email;

        private String senha;

        public UsuarioResponseDto() {
        }

        public UsuarioResponseDto(Long id, String nome, String cpf, Integer idade, String cidade, String uf, LocalDate dataCadastro, String email, String senha) {
                this.id = id;
                this.nome = nome;
                this.cpf = cpf;
                this.idade = idade;
                this.cidade = cidade;
                this.uf = uf;
                this.dataCadastro = dataCadastro;
                this.email = email;
                this.senha = senha;
        }


        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getSenha() {
                return senha;
        }

        public void setSenha(String senha) {
                this.senha = senha;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
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
}