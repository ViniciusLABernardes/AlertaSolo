package br.com.AlertaSolo.repository;


import br.com.AlertaSolo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    List<Usuario> findByCidadeAndUf(String cidade, String uf);
}
