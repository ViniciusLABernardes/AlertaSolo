package br.com.AlertaSolo.services;

import br.com.UWbike.entity.Moto;
import br.com.UWbike.exceptions.IdNaoEncontradoException;
import br.com.UWbike.repository.MotoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    public Moto salvarMoto(Moto moto){
        Moto motoNova = new Moto();
        try {
            System.out.println("Moto cadastrada com sucesso!");
           motoNova = motoRepository.save(moto);
        }catch (Exception e){
            System.out.println("Houve um erro ao tentar cadastrar a moto");
           e.printStackTrace();
        }
        return motoNova;
    }

    public void removerMoto(Long id) throws IdNaoEncontradoException{
        Moto motoAchada = motoRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Moto não encontrada"));

                motoRepository.deleteById(id);

            System.out.println("Moto: " + motoAchada.getId_moto() + ", " + motoAchada.getPlaca() + " deletada com sucesso!");

    }

    @Transactional
    public void atualizarDadosMoto(Long id, Moto moto) throws IdNaoEncontradoException{
        Moto motoAchada = motoRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Moto não encontrada"));

        motoAchada.setPlaca(moto.getPlaca());

        System.out.println("Moto: " + motoAchada.getId_moto() + ", "
                + " atualizada com sucesso para: " + moto.getPlaca() + " " + moto.getModelo());

    }

    public Optional<Moto> visualizarDadosMotoEspecifica(Long id) {
      return motoRepository.findById(id);


    }

}
