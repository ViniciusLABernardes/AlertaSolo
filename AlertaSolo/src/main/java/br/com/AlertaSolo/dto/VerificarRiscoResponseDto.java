package br.com.AlertaSolo.dto;

import java.util.List;

public class VerificarRiscoResponseDto {

    private boolean riscoAlto;

    private String mensagem;

    private List<String> notificacoes;

    public VerificarRiscoResponseDto() {
    }

    public VerificarRiscoResponseDto(boolean riscoAlto, String mensagem, List<String> notificacoes) {
        this.riscoAlto = riscoAlto;
        this.mensagem = mensagem;
        this.notificacoes = notificacoes;
    }

    public boolean isRiscoAlto() {
        return riscoAlto;
    }

    public void setRiscoAlto(boolean riscoAlto) {
        this.riscoAlto = riscoAlto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<String> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<String> notificacoes) {
        this.notificacoes = notificacoes;
    }
}
