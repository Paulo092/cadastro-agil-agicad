package br.com.jumpcat.agicad.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumpcat.agicad.dtos.ServicoDTO;
import br.com.jumpcat.agicad.exceptions.BusinessException;
import br.com.jumpcat.agicad.models.Servico;
import br.com.jumpcat.agicad.repositories.ServicoDAO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GestaoServico{
	
	private ServicoDAO dao;
	
	@Transactional(readOnly = true)
	public Page<ServicoDTO> findAll(Pageable pageable) {	
		Page<Servico> result = dao.findAll(pageable);
	
		return result.map(obj -> new ServicoDTO(obj));
	}

	@Transactional(readOnly = true)
	public ServicoDTO findById(Integer id) {
		Servico result = dao.findById(id)
				.orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
		
		return new ServicoDTO(result);
	}
	
	@Transactional(readOnly = true)
	public ServicoDTO findByNome(String nome) {
		Servico result = dao.findByNome(nome).
				orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
		
		return new ServicoDTO(result);
    }
	
	@Transactional
	public ServicoDTO update(ServicoDTO obj) {
		Servico entity = dao.findById(obj.getCodigo())
				.orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
	
		entity.setDescricao(obj.getDescricao());
		entity.setNome(obj.getNome());
		
		return new ServicoDTO(dao.save(entity));
	}	
	
	
	@Transactional
	public ServicoDTO save(ServicoDTO obj) {
		Servico entity = new Servico(
						obj.getCodigo(), 
						obj.getNome(), 
						obj.getDescricao());
		
		Boolean nameExists = dao.findByNome(entity.getNome()).stream()
					.anyMatch(objResult -> !objResult.equals(entity));
		
		if(nameExists) throw new BusinessException("Este servico ja esta cadastrado!");
		return new ServicoDTO(dao.save(entity));
	}
	
	@Transactional
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
	
	public boolean existById(Integer id) {
		return dao.existsById(id);
	}

}