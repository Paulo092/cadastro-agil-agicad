package br.com.jumpcat.agicad.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jumpcat.agicad.models.Prestador;

public interface PrestadorDAO extends JpaRepository<Prestador, Integer>{
	Optional<Prestador> findByEndereco(String endereco);
}
