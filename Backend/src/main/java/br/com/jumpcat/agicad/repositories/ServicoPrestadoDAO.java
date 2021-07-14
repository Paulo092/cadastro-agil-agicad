package br.com.jumpcat.agicad.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jumpcat.agicad.models.Prestador;
import br.com.jumpcat.agicad.models.Servico;
import br.com.jumpcat.agicad.models.ServicoPrestado;

public interface ServicoPrestadoDAO extends JpaRepository<ServicoPrestado, Integer>{
	List<ServicoPrestado> findByPrestador(Prestador prestador);
	List<ServicoPrestado> findByServico(Servico servico);
}
