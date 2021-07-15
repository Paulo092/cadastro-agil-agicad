package br.com.jumpcat.agicad.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumpcat.agicad.dtos.UsuarioDTO;
import br.com.jumpcat.agicad.exceptions.BusinessException;
import br.com.jumpcat.agicad.models.Prestador;
import br.com.jumpcat.agicad.models.Usuario;
import br.com.jumpcat.agicad.repositories.PrestadorDAO;
import br.com.jumpcat.agicad.repositories.UsuarioDAO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GestaoUsuario {
	
	private UsuarioDAO dao;
	private PrestadorDAO prestDao;
	
	@Transactional(readOnly = true)
	public Page<UsuarioDTO> findAll(Pageable pageable) {	
		Page<Usuario> result = dao.findAll(pageable);
	
		return result.map(obj -> new UsuarioDTO(obj));
	}
	
	@Transactional(readOnly = true)
	public UsuarioDTO findById(Integer id) {
		Usuario result = dao.findById(id).
				orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
		
		return new UsuarioDTO(result);		
	}
	
	@Transactional(readOnly = true)
	public UsuarioDTO findByLogin(String login) {
		Usuario result = dao.findByLogin(login).
				orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
		
		return new UsuarioDTO(result);
	}
	
	@Transactional(readOnly = true)
	public UsuarioDTO findByEmail(String email) {
		Usuario result = dao.findByEmail(email).
				orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
		
		return new UsuarioDTO(result);
	}
	
	@Transactional(readOnly = true)
	public UsuarioDTO findByNome(String nome) {
		Usuario result = dao.findByNome(nome).
				orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
		
		return new UsuarioDTO(result);
    }
	
	@Transactional
	public UsuarioDTO update(UsuarioDTO obj) {
		Usuario entity = dao.findById(obj.getCodigo())
				.orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
		
		entity.setEmail(obj.getEmail());
		entity.setLogin(obj.getLogin());
		entity.setSenha(obj.getSenha());
		entity.setNome(obj.getNome());
		
		return new UsuarioDTO(dao.save(entity));
	}	
	
	@Transactional
	public UsuarioDTO save(UsuarioDTO obj) {
		Usuario entity = new Usuario(
					obj.getCodigo(),
					obj.getLogin(),
					obj.getEmail(),
					obj.getSenha(),
					obj.getNome()
				);
		
		Boolean loginExists = dao.findByLogin(entity.getLogin()).stream()
				.anyMatch(objResult -> !objResult.equals(entity));
		Boolean emailExists = dao.findByEmail(entity.getEmail()).stream()
				.anyMatch(objResult -> !objResult.equals(entity));
		
		if(loginExists) throw new BusinessException("Este login ja esta em uso");
		if(emailExists) throw new BusinessException("Este email ja esta em uso");
		
		return new UsuarioDTO(dao.save(entity));
	}
	
	@Transactional
	public void deleteById(Integer id) {
		Usuario search = dao.findById(id).orElse(null);

		Optional<Prestador> prestSyncId = prestDao.findByUsuario(search);
		prestSyncId.stream().forEach(obj -> prestDao.deleteById(obj.getCodigo()));
		
		dao.deleteById(id);
	}
	
	public boolean existById(Integer id) {
		return dao.existsById(id);
	}

}