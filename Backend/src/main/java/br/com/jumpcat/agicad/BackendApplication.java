package br.com.jumpcat.agicad;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import br.com.jumpcat.agicad.models.Prestador;
import br.com.jumpcat.agicad.models.Servico;
import br.com.jumpcat.agicad.models.ServicoPrestado;
import br.com.jumpcat.agicad.models.Usuario;
import br.com.jumpcat.agicad.repositories.PrestadorDAO;
import br.com.jumpcat.agicad.repositories.ServicoDAO;
import br.com.jumpcat.agicad.repositories.ServicoPrestadoDAO;
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
	
	@Autowired
	ServicoPrestadoDAO spDAO;
	
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Usuario u1 = new Usuario(1, "JiggleWiglle", "email1@email", "12345", "Mickeson");
		Usuario u2 = new Usuario(2, "JohnDoe556", "email2@email", "54321", "John Doe");
		Usuario u3 = new Usuario(3, "Mebow", "mebow@email", "meebow", "Mikel Mebol");
		Usuario u4 = new Usuario(4, "Trande", "trand@email", "123", "Tran Demais");
		
		Servico s1 = new Servico(1, "Dentista", "Consultorio Oftalmo");
		Servico s2 = new Servico(2, "Mecanico", "Conserto de Veiculos");
		Servico s3 = new Servico(3, "Manicure", "Pinta unha");
		Servico s4 = new Servico(4, "Trader", "Profissional do chute");
		
		Prestador p1 = new Prestador(1, "Rua 1", u3, null);
		Prestador p2 = new Prestador(2, "Rua Masnu", u1, null);
		Prestador p3 = new Prestador(3, "Rua Mi Olhando", u4, null);
		
		ServicoPrestado sp1 = new ServicoPrestado(1, 20.00, p1, s1);
		ServicoPrestado sp2 = new ServicoPrestado(2, 65.00, p3, s4);
		
		usuarioDao.saveAll(Arrays.asList(u1, u2, u3, u4));
		servicoDao.saveAll(Arrays.asList(s1, s2, s3, s4));
		prestadorDao.saveAll(Arrays.asList(p1, p2, p3));
		spDAO.saveAll(Arrays.asList(sp1, sp2));
	}

}
