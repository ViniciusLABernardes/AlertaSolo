package br.com.AlertaSolo.services;

import br.com.UWbike.entity.Ancora;
import br.com.UWbike.entity.Patio;
import br.com.UWbike.exceptions.IdNaoEncontradoException;
import br.com.UWbike.repository.AncoraRepository;
import br.com.UWbike.repository.PatioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SensorService {

    @Autowired
    private PatioRepository patioRepository;
    @Autowired
    private AncoraRepository ancoraRepository;

    public Ancora salvarAncoraComPatio(Ancora ancora, Long idPatio) throws IdNaoEncontradoException {
        Ancora ancoraNova = new Ancora();
        try {
            Patio patio = patioRepository.findById(idPatio)
                    .orElseThrow(() -> new IdNaoEncontradoException("Pátio com id " + idPatio + " não encontrado"));

            ancora.setPatio(patio);
            ancoraNova = ancoraRepository.save(ancora);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ancoraNova;
    }

    public void removerAncora(Long id) throws IdNaoEncontradoException {
        Ancora ancoraAchada = ancoraRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Ancora não encontrada"));

        ancoraRepository.deleteById(id);

        System.out.println("Ancora: " + ancoraAchada.getId() + ", " + ancoraAchada.getX() + " " + ancoraAchada.getY() + " deletada com sucesso!");

    }

    @Transactional
    public void atualizarDadosAncora(Long id, Ancora ancora) throws IdNaoEncontradoException{
        Ancora ancoraAchada = ancoraRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Ancora não encontrada"));

        ancoraAchada.setX(ancora.getX());
        ancoraAchada.setY(ancora.getY());

        System.out.println("Ancora: " + ancoraAchada.getId() + ", "
                + " atualizada com sucesso para: " + ancora.getX() + " " + ancora.getY());

    }

    public Optional<Ancora> visualizarDadosAncoraEspecifica(Long id) {
        return ancoraRepository.findById(id);

    }
}
