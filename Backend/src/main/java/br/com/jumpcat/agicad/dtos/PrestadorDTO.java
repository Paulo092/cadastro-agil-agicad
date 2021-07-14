package br.com.jumpcat.agicad.dtos;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.jumpcat.agicad.models.Prestador;
import br.com.jumpcat.agicad.models.ServicoPrestado;
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
@JsonPropertyOrder({"codigo_prestador", "endereco_prestador"})
public class PrestadorDTO extends RepresentationModel<PrestadorDTO> implements Serializable {
	
	private static final long serialVersionUID = 1L;

//	@NotNull(groups = ValidationGroups.UsuarioId.class)
	@EqualsAndHashCode.Include
	@JsonProperty("codigo_prestador")
	private Integer codigo;

	@NotBlank
	@Size(max = 60)
	@JsonProperty("endereco_prestador")
	private String endereco;
	
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.UsuarioId.class)
	@NotNull
	private UsuarioDTO usuario;
	
	private List<ServicoPrestado> servicos;
	
	public PrestadorDTO(Prestador obj) {
		this.codigo = obj.getCodigo();
		this.endereco = obj.getEndereco();
		
		this.usuario = new UsuarioDTO(obj.getUsuario());
//		this.servicos = obj.getServicosPrestados();
	}
}