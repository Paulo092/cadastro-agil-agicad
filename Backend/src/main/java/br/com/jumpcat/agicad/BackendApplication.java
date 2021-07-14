package br.com.jumpcat.agicad;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import br.com.jumpcat.agicad.models.Prestador;
import br.com.jumpcat.agicad.models.Servico;
import br.com.jumpcat.agicad.models.Usuario;
import br.com.jumpcat.agicad.repositories.PrestadorDAO;
import br.com.jumpcat.agicad.repositories.ServicoDAO;
import br.com.jumpcat.agicad.repositories.UsuarioDAO;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
public class BackendApplication implements CommandLineRunner {

	@Autowired
	ServicoDAO servicoDao;

	@Autowired
	UsuarioDAO usuarioDao;
	
	@Autowired
	PrestadorDAO prestadorDao;
	
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Usuario userTemp = new Usuario(3, "Mebow", "mebow@email", "meebow", "Mikel Mebol");
		
		List<Servico> servicos = Arrays.asList(
			new Servico(1, "Dentista", "Consultorio Oftalmo"),
			new Servico(2, "Mecanico", "Conserto de Veiculos")
		);
		
		List<Prestador> prestadores = Arrays.asList(
			new Prestador(1, "Rua 1", userTemp, null)
		);
		
		List<Usuario> usuarios = Arrays.asList(
			new Usuario(1, "JiggleWiglle", "email1@email", "12345", "Mickeson"),
			new Usuario(2, "JohnDoe556", "email2@email", "54321", "John Doe"),
			userTemp
		);
		
		usuarioDao.saveAll(usuarios);
		servicoDao.saveAll(servicos);
		prestadorDao.saveAll(prestadores);
	}

}
