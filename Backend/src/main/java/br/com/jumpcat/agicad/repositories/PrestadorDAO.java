package br.com.jumpcat.agicad.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jumpcat.agicad.models.Prestador;
import br.com.jumpcat.agicad.models.Usuario;

public interface PrestadorDAO extends JpaRepository<Prestador, Integer>{
	Optional<Prestador> findByEndereco(String endereco);
	Optional<Prestador> findByUsuario(Usuario usuario);
}
