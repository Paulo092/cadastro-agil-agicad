package br.com.jumpcat.agicad.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jumpcat.agicad.models.Servico;

public interface ServicoDAO extends JpaRepository<Servico, Integer> { 
	public Optional<Servico> findByNome(String nome);
}
