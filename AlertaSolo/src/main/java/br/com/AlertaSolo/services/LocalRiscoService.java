package br.com.AlertaSolo.services;


import br.com.AlertaSolo.entity.LocalRisco;
import br.com.AlertaSolo.exceptions.IdNaoEncontradoException;
import br.com.AlertaSolo.repository.LocalRiscoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocalRiscoService {

    @Autowired
    private LocalRiscoRepository localRiscoRepository;

    public LocalRisco salvarLocalRisco(LocalRisco local) {
        try {

            localRiscoRepository.save(local);
        } catch (Exception e) {
            System.out.println("Houve um erro ao tentar cadastrar o local:");
            e.printStackTrace();
        }
        return local;
    }

    public void removerLocalRisco(Long id) throws IdNaoEncontradoException {

        LocalRisco local = localRiscoRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("local não encontrado"));
            localRiscoRepository.deleteById(id);
            System.out.println("LocalRisco: " + local.getNomeLocal() + ", " + local.getCidade() + " deletado com sucesso!");

    }

    @Transactional
    public void atualizarDadosLocalRisco(Long id, LocalRisco local) throws IdNaoEncontradoException {
        LocalRisco localAchada = localRiscoRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Local não encontrado"));

        localAchada.setGrauRisco(local.getGrauRisco());
        localAchada.setAtivo(local.getAtivo());



        System.out.println("Local: " + localAchada.getIdLocal() + ", "  + local.getNomeLocal()
                + " atualizado com sucesso: "
                + local.getGrauRisco() + " " + local.getAtivo());

    }

    public Optional<LocalRisco> visualizarDadosLocalRiscoEspecifico(Long id)  {
        return localRiscoRepository.findById(id);
    }

    public List<MotoResponseDto> visualizarMotosQueJaPassaramEstaoNoLocalRisco(Long idLocalRisco) throws IdNaoEncontradoException {
        List<Moto> motos = localRiscoRepository.findMotosByLocalRisco(idLocalRisco);

        if (motos == null || motos.isEmpty()) {
            throw new IdNaoEncontradoException("Nenhuma moto encontrada para o pátio com id " + idLocalRisco);
        }

        return motos.stream()
                .map(moto -> new MotoResponseDto(
                        moto.getId_moto(),
                        moto.getModelo(),
                        moto.getPlaca(),
                        moto.getChassi()))
                .toList();
    }

    public List<LocalRiscoResponseDto> listarLocalRiscos() {
        try {
            return localRiscoRepository.findAll()
                    .stream()
                    .map(local -> new LocalRiscoResponseDto(
                            local.getIdLocalRisco(),
                            local.getLogradouro(),
                            local.getNumero(),
                            local.getComplemento(),
                            local.getCep(),
                            local.getCidade(),
                            local.getUf(),
                            local.getPais(),
                            local.getLotacao()
                            ))
                    .toList();
        } catch (Exception e) {
            System.out.println("Houve um erro ao tentar resgatar todos os pátios: ");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


}
