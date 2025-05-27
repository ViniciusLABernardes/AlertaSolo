package br.com.AlertaSolo.services;


import br.com.UWbike.entity.Ancora;
import br.com.UWbike.entity.Moto;
import br.com.UWbike.entity.MotoPatio;
import br.com.UWbike.entity.Patio;
import br.com.UWbike.exceptions.IdNaoEncontradoException;
import br.com.UWbike.repository.AncoraRepository;
import br.com.UWbike.repository.MotoPatioRepository;
import br.com.UWbike.repository.MotoRepository;
import br.com.UWbike.repository.PatioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MotoPatioService {
    @Autowired
    private MotoPatioRepository motoPatioRepository;

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private PatioRepository patioRepository;

    @Autowired
    private AncoraRepository ancoraRepository;

    public MotoPatio adicionarMotoAoPatio(Long idMoto, Long idPatio) throws IdNaoEncontradoException {
        Moto moto = motoRepository.findById(idMoto)
                .orElseThrow(() -> new IdNaoEncontradoException("Moto com ID " + idMoto + " não encontrada."));

        Patio patio = patioRepository.findById(idPatio)
                .orElseThrow(() -> new IdNaoEncontradoException("Pátio com ID " + idPatio + " não encontrado."));

        MotoPatio motoPatio = new MotoPatio();
        motoPatio.setMoto(moto);
        motoPatio.setPatio(patio);
        motoPatio.setDataHoraEntrada(LocalDateTime.now());

        return motoPatioRepository.save(motoPatio);
    }


    public MotoPatio adicionarSaidaMoto(Long idMoto) throws IdNaoEncontradoException{
        MotoPatio entradaAberta = motoPatioRepository
                .findEntradaAbertaDaMoto(idMoto)
                .orElseThrow(() -> new IdNaoEncontradoException("Nenhuma entrada em aberto encontrada para a moto ID " + idMoto));

        entradaAberta.setDataHoraSaida(LocalDateTime.now());
        return motoPatioRepository.save(entradaAberta);
    }


    public Moto calcularPosicaoMoto(long idMoto,long idPatio, double d1, double d2, double d3) {
        Patio patio = patioRepository.findById(idPatio)
                .orElseThrow(() -> new RuntimeException("Pátio não encontrado"));

       Moto moto = motoRepository.findById(idMoto) .orElseThrow(() -> new RuntimeException("Moto não encontrada"));
        List<Ancora> ancoras = patio.getAncoras();

        if (ancoras.size() < 3) {
            throw new RuntimeException("É necessário ter pelo menos 3 âncoras no pátio.");
        }

        // Pegando as 3 primeiras âncoras do pátio
        Ancora a1 = ancoras.get(0);
        Ancora a2 = ancoras.get(1);
        Ancora a3 = ancoras.get(2);

        // Coordenadas das âncoras
        double x1 = a1.getX(), y1 = a1.getY();
        double x2 = a2.getX(), y2 = a2.getY();
        double x3 = a3.getX(), y3 = a3.getY();

        // Usando apenas 3 âncoras para trilateração 2D
        return trilaterar(moto, x1, y1, d1, x2, y2, d2, x3, y3, d3);
    }

    private Moto trilaterar(Moto moto,double x1, double y1, double r1,
                            double x2, double y2, double r2,
                            double x3, double y3, double r3) {

        double A = 2 * (x2 - x1);
        double B = 2 * (y2 - y1);
        double C = r1 * r1 - r2 * r2 - x1 * x1 + x2 * x2 - y1 * y1 + y2 * y2;

        double D = 2 * (x3 - x2);
        double E = 2 * (y3 - y2);
        double F = r2 * r2 - r3 * r3 - x2 * x2 + x3 * x3 - y2 * y2 + y3 * y3;

        double denominator = A * E - B * D;

        if (denominator == 0) {
            throw new RuntimeException("As âncoras estão mal posicionadas para trilateração.");
        }

        double x = (C * E - F * B) / denominator;
        double y = (A * F - C * D) / denominator;

        moto.setPosicaoX(x);
        moto.setPosicaoY(y);

        return moto;


    }


}



