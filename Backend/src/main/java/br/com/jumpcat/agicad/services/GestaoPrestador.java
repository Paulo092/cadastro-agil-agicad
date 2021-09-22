package br.com.jumpcat.agicad.services;



import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumpcat.agicad.dtos.PrestadorDTO;
import br.com.jumpcat.agicad.models.Prestador;
import br.com.jumpcat.agicad.models.Usuario;
import br.com.jumpcat.agicad.repositories.PrestadorDAO;
import br.com.jumpcat.agicad.repositories.UsuarioDAO;
import br.com.jumpcat.agicad.services.exceptions.BusinessException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GestaoPrestador {
	
	private PrestadorDAO dao;
	
	private UsuarioDAO userDao;
	
	@Transactional(readOnly = true)
	public Page<PrestadorDTO> findAll(Pageable pageable) {	
		Page<Prestador> result = dao.findAll(pageable);
		
		return result.map(obj -> new PrestadorDTO(obj));
	}

	@Transactional(readOnly = true)
	public PrestadorDTO findById(Integer id) {
		Prestador result = dao.findById(id).
				orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
		
		return new PrestadorDTO(result);
	}
	
	@Transactional(readOnly = true)
	public PrestadorDTO findByEndereco(String endereco) {
		Prestador result = dao.findByEndereco(endereco).
				orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
		
		return new PrestadorDTO(result);
	}
	
	@Transactional
	public PrestadorDTO update(PrestadorDTO obj) {
		Prestador entity = dao.findById(obj.getCodigo())
				.orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
		
		entity.setEndereco(obj.getEndereco());
		
		return new PrestadorDTO(dao.save(entity));
	}	
	
	
	@Transactional
	public PrestadorDTO save(PrestadorDTO obj) {
		Prestador entity = new Prestador(obj.getCodigo(), 
									obj.getEndereco(),
									new Usuario(
											obj.getUsuario().getCodigo(),
											obj.getUsuario().getLogin(),
											obj.getUsuario().getEmail(),
											obj.getUsuario().getSenha(),
											obj.getUsuario().getNome()),
									null);
	
		Optional<Usuario> user = userDao.findById(obj.getUsuario().getCodigo());
		
		entity.setUsuario(user.orElseThrow(() -> new BusinessException("Um prestador de servicos deve estar vinculado a um usuario")));
		
		boolean enderecoExists = dao.findByEndereco(entity.getEndereco())
				.stream()
				.anyMatch(objResult -> !objResult.equals(entity));
	
		if (enderecoExists) throw new BusinessException("Este endereco ja esta sendo utilizado");
	
		return new PrestadorDTO(dao.save(entity));
	}
	
	@Transactional
	public void deleteById(Integer id) {
		
		
		dao.deleteById(id);
	}
	
	public boolean existById(Integer id) {
		return dao.existsById(id);
	}
}