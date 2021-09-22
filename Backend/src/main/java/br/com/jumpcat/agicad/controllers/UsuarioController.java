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
import org.springframework.web.bind.annotation.CrossOrigin;
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

import br.com.jumpcat.agicad.dtos.UsuarioDTO;
import br.com.jumpcat.agicad.services.GestaoUsuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/v1/agicad/usuarios")
@Tag(name = "Endpoint de Usuario")
public class UsuarioController {

	@Autowired
	private GestaoUsuario service;
	
	@GetMapping
	@Operation(summary = "Busca todos os usuarios")
	public ResponseEntity<CollectionModel<UsuarioDTO>> fetchAll(
		@RequestParam(value="page", defaultValue = "0") int page,
		@RequestParam(value="limit", defaultValue = "12") int limit,
		@RequestParam(value="direction", defaultValue = "asc") String direction) {
			
			Direction sortDirection	 = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
				
			Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "codigo"));
				
			Page<UsuarioDTO> pages = service.findAll(pageable);
			pages
				.stream()
				.forEach(p -> p.add(
						linkTo(methodOn(UsuarioController.class).fetchOne(p.getCodigo())).withSelfRel()
					)
				);
			  	
			return ResponseEntity.ok(CollectionModel.of(pages));
	}

	@GetMapping("/{id}")
	@Operation(summary = "Busca um usuario pelo id")
	public ResponseEntity<UsuarioDTO> fetchOne(@PathVariable Integer id) {
			UsuarioDTO objDTO = service.findById(id);
			objDTO.add(linkTo(methodOn(UsuarioController.class).fetchOne(id)).withSelfRel());
			return ResponseEntity.ok(objDTO);
	}
	
	@GetMapping("/login/{login}")
	@Operation(summary = "Busca um usuario pelo login")
	public ResponseEntity<UsuarioDTO> findByLogin(@PathVariable String login) {
		UsuarioDTO objDTO = service.findByLogin(login);
		objDTO.add(linkTo(methodOn(UsuarioController.class).findByLogin(login)).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}	
	
	@GetMapping("/email/{email}")
	@Operation(summary = "Busca um usuario pelo email")
	public ResponseEntity<UsuarioDTO> findByEmail(@PathVariable String email) {
		UsuarioDTO objDTO = service.findByEmail(email);
		objDTO.add(linkTo(methodOn(UsuarioController.class).findByEmail(email)).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}
	
	@GetMapping("/nome/{nome}")
	@Operation(summary = "Busca um usuario pelo nome")
	public ResponseEntity<UsuarioDTO> findByNome(@PathVariable String nome) {
		UsuarioDTO objDTO = service.findByNome(nome);
		objDTO.add(linkTo(methodOn(UsuarioController.class).findByNome(nome)).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}	
	  
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Insere um novo usuario")
	public ResponseEntity<UsuarioDTO> insert(@RequestBody @Valid UsuarioDTO objBody) {
		UsuarioDTO objDTO = service.save(objBody);
		objDTO.add(linkTo(methodOn(UsuarioController.class).fetchOne(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}

	@PutMapping
	@Operation(summary = "Atualiza um usuario pelo id")
	public ResponseEntity<UsuarioDTO> update(@RequestBody @Valid UsuarioDTO objBody) {
		UsuarioDTO objDTO = service.update(objBody);
		objDTO.add(linkTo(methodOn(UsuarioController.class).fetchOne(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}	

	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui um usuario pelo id")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		if (!service.existById(id)) {
			return ResponseEntity.notFound().build();
		}

		service.deleteById(id);

		return ResponseEntity.noContent().build();

	}
}
