package br.com.jumpcat.agicad.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumpcat.agicad.dtos.ServicoDTO;
import br.com.jumpcat.agicad.services.GestaoServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/agicad/servicos")
@Tag(name = "Endpoint de Servico")
public class ServicoController {

	@Autowired
	private GestaoServico service;
	
	@GetMapping
	@Operation(summary = "Busca todos os servicos")
	public ResponseEntity<CollectionModel<ServicoDTO>> fetchAll(
		@RequestParam(value="page", defaultValue = "0") int page,
		@RequestParam(value="limit", defaultValue = "12") int limit,
		@RequestParam(value="direction", defaultValue = "asc") String direction) {
			
			Direction sortDirection	 = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
				
			Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "codigo"));
				
			Page<ServicoDTO> pages = service.findAll(pageable);
			pages
				.stream()
				.forEach(p -> p.add(
						linkTo(methodOn(ServicoController.class).fetchOne(p.getCodigo())).withSelfRel()
					)
				);
			  	
			return ResponseEntity.ok(CollectionModel.of(pages));
	}

	@GetMapping("/{id}")
	@Operation(summary = "Busca um servico por id")
	public ResponseEntity<ServicoDTO> fetchOne(@PathVariable Integer id) {
			ServicoDTO objDTO = service.findById(id);
			objDTO.add(linkTo(methodOn(ServicoController.class).fetchOne(id)).withSelfRel());
			return ResponseEntity.ok(objDTO);
		}
	  
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Insere um novo servico")
	public ResponseEntity<ServicoDTO> insert(@RequestBody @Valid ServicoDTO objBody) {
		ServicoDTO objDTO = service.save(objBody);
		
		objDTO.add(linkTo(methodOn(ServicoController.class).fetchOne(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}

	@PutMapping
	@Operation(summary = "Atualiza um servico por id")
	public ResponseEntity<ServicoDTO> update(@RequestBody @Valid ServicoDTO objBody) {
		
		ServicoDTO objDTO = service.update(objBody);
		objDTO.add(linkTo(methodOn(ServicoController.class).fetchOne(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}	

	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui um servico por id")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		if (!service.existById(id)) {
			return ResponseEntity.notFound().build();
		}

		service.deleteById(id);

		return ResponseEntity.noContent().build();

	}
}
