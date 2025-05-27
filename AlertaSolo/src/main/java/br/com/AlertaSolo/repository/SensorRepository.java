package br.com.AlertaSolo.repository;


import br.com.AlertaSolo.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor,Long> {

}
