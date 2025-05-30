package br.com.AlertaSolo.repository;


import br.com.AlertaSolo.entity.LocalRisco;
import br.com.AlertaSolo.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SensorRepository extends JpaRepository<Sensor,Long> {
    Optional<Sensor> findByLocalRisco(LocalRisco localRisco);

}
