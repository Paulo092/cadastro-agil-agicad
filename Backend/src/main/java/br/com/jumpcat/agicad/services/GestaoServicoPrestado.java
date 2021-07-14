package br.com.jumpcat.agicad.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumpcat.agicad.dtos.ServicoPrestadoDTO;
import br.com.jumpcat.agicad.models.Prestador;
import br.com.jumpcat.agicad.models.Servico;
import br.com.jumpcat.agicad.models.ServicoPrestado;
import br.com.jumpcat.agicad.repositories.PrestadorDAO;
import br.com.jumpcat.agicad.repositories.ServicoDAO;
import br.com.jumpcat.agicad.repositories.ServicoPrestadoDAO;
import br.com.jumpcat.agicad.services.exceptions.BusinessException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GestaoServicoPrestado{
	
	private ServicoPrestadoDAO dao;
	
	private ServicoDAO servDao;
	private PrestadorDAO prestDao;
	
	
	@Transactional(readOnly = true)
	public Page<ServicoPrestadoDTO> findAll(Pageable pageable) {	
		Page<ServicoPrestado> result = dao.findAll(pageable);
	
		return result.map(obj -> new ServicoPrestadoDTO(obj));
	}
	
//	@Transactional(readOnly = true)
//	public ServicoDTO findByNome(String nome) {
//		Servico result = dao.findByNome(nome).
//				orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
//		
//		return new ServicoDTO(result);
//    }
	
	@Transactional
	public ServicoPrestadoDTO link(ServicoPrestadoDTO obj) {
		
		ServicoPrestado entity = new ServicoPrestado(
						obj.getCodigo(),
						obj.getPreco(),
						null,
						null);		
		
		Optional<Prestador> prest = prestDao.findById(obj.getPrestador().getCodigo());
		entity.setPrestador(prest.orElseThrow(() -> new BusinessException("O prestador inserido nao existe")));
		
		Optional<Servico> serv = servDao.findById(obj.getServico().getCodigo());
		entity.setServico(serv.orElseThrow(() -> new BusinessException("O servico inserido nao existe")));
		
		return new ServicoPrestadoDTO(dao.save(entity));
	}
	
//	@Transactional
//	public void deleteById(Integer id) {
//		dao.deleteById(id);
//	}
	
	public boolean existById(Integer id) {
		return dao.existsById(id);
	}

}