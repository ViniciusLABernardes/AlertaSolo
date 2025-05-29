package br.com.AlertaSolo.services;


import br.com.AlertaSolo.entity.LocalRisco;
import br.com.AlertaSolo.entity.Sensor;
import br.com.AlertaSolo.entity.Usuario;
import br.com.AlertaSolo.exceptions.IdNaoEncontradoException;
import br.com.AlertaSolo.repository.LocalRiscoRepository;
import br.com.AlertaSolo.repository.SensorRepository;
import br.com.AlertaSolo.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

    @Autowired
    private LocalRiscoRepository localRiscoRepository;
    @Autowired
    private SensorRepository sensorRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Sensor salvarSensor(Sensor sensor, long idLocalRisco) throws IdNaoEncontradoException {
        Sensor sensorNova = new Sensor();
        try {
            LocalRisco localRisco = localRiscoRepository.findById(idLocalRisco)
                    .orElseThrow(() -> new IdNaoEncontradoException("Local com id " + idLocalRisco + " não encontrado"));

            sensor.setLocalRisco(localRisco);
            sensorNova = sensorRepository.save(sensor);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return sensorNova;
    }

    public void removerSensor(long id) throws IdNaoEncontradoException {
        Sensor sensorAchada = sensorRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Sensor não encontrado"));

        sensorRepository.deleteById(id);

        System.out.println("Sensor: " + sensorAchada.getIdSensor() + ", " + sensorAchada.getCodigoEsp32() + " " + sensorAchada.getDataInstalacao() + " deletado com sucesso!");

    }

    public Optional<Sensor> visualizarDadosSensorEspecifico(long id)  {
        return sensorRepository.findById(id);

    }

    @Transactional
    public void verificarRiscoDeslizamento(long idLocal,double umidade, double inclinacao, double tremor) {
        boolean umidadeAlta = umidade >= 80; //se maior que 80% significa alta saturação no solo
        boolean inclinacaoPerigosa = inclinacao >= 45; // se inclinação maior que 45(graus) ocorre o risco de escorregamento
        boolean tremorSignificativo = tremor >= 4.0; // se maior que 4.0 na escala Richter se torna um potencial desestabilizador do solo

        if (umidadeAlta || inclinacaoPerigosa || tremorSignificativo) {
            System.out.println("⚠️ RISCO ALTO DETECTADO!");
            List<Usuario> usuarios = usuarioRepository.findAll();
            for (Usuario usuario : usuarios) {
                System.out.println("🔔 Notificar " + usuario.getNome());
            }
        } else {
            System.out.println("✅ Situação estável.");
        }
    }

}
