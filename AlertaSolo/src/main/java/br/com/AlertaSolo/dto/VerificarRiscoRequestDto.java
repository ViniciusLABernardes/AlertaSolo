package br.com.AlertaSolo.dto;
import jakarta.validation.constraints.NotNull;

public class VerificarRiscoRequestDto {

        @NotNull(message = "id do local é obrigatório!")
        private long idLocal;

        @NotNull(message = "valor decimal de umidade obrigatório")
        private double umidade;

        @NotNull(message = "valor decimal da inclinação obrigatório")
        private double inclinacao;

        @NotNull(message = "valor decimal do tremor obrigatório")
        private double tremor;

        public VerificarRiscoRequestDto() {}

        public VerificarRiscoRequestDto(long idLocal,double umidade, double inclinacao, double tremor) {
            this.idLocal = idLocal;
            this.umidade = umidade;
            this.inclinacao = inclinacao;
            this.tremor = tremor;
        }

        public long getIdLocal() {
            return idLocal;
        }

        public void setIdLocal(long idLocal) {
            this.idLocal = idLocal;
        }

    public double getUmidade() {
            return umidade;
        }

        public void setUmidade(double umidade) {
            this.umidade = umidade;
        }

        public double getInclinacao() {
            return inclinacao;
        }

        public void setInclinacao(double inclinacao) {
            this.inclinacao = inclinacao;
        }

        public double getTremor() {
            return tremor;
        }

        public void setTremor(double tremor) {
            this.tremor = tremor;
        }
}

