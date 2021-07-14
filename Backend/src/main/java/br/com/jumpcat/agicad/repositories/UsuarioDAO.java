package br.com.jumpcat.agicad.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jumpcat.agicad.models.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {
	public Optional<Usuario> findByLogin(String login);
	public Optional<Usuario> findByEmail(String email);
	public Optional<Usuario> findByNome(String nome);
}
