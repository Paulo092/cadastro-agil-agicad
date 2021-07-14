package br.com.jumpcat.agicad.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.jumpcat.agicad.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@JsonPropertyOrder({"codigo_usuario", "login_usuario", "email_usuario", "senha_usuario", "nome_usuario"})
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@JsonProperty("codigo_usuario")
	@NotNull(groups = ValidationGroups.UsuarioId.class)
	private Integer codigo;

	@NotBlank
	@Size(max = 45)
	@JsonProperty("login_usuario")
	private String login;
	
	@Email
	@NotBlank
	@Size(max = 80)
	@JsonProperty("email_usuario")	
	private String email;
	
	@NotBlank
	@Size(max = 80)
	@JsonProperty("senha_usuario")	
	private String senha;
	
	@NotBlank
	@Size(max = 80)
	@JsonProperty("nome_usuario")
	private String nome;

//	private PrestadorDTO prestador;
	
	public UsuarioDTO(Usuario obj) {
		this.codigo = obj.getCodigo();
		this.email = obj.getEmail();
		this.login = obj.getLogin();
		this.nome = obj.getNome();
		this.senha = obj.getSenha();
		
//		this.prestador =  obj.getPrestador() != null ? new PrestadorDTO(obj.getPrestador()) : null;
	}
}