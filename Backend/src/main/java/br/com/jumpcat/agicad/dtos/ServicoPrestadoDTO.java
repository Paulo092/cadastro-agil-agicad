package br.com.jumpcat.agicad.dtos;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
@JsonPropertyOrder({"prestador", "servico", "preco"})
public class ServicoPrestadoDTO extends RepresentationModel<ServicoPrestadoDTO> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@JsonProperty("codigo")
	private Integer codigo;
	
	@JsonProperty("preco")
	private Double preco;
	
	@JsonProperty("prestador")
	private PrestadorDTO prestador;
	
	@JsonProperty("servico")
	private ServicoDTO servico;

//	@NotBlank
//	@Size(max = 60)
	
//	@Valid
//	@NotNull
//	@ConvertGroup(from = Default.class, to = ValidationGroups.UsuarioId.class)
	
	public ServicoPrestadoDTO(ServicoPrestado obj) {
		this.codigo = obj.getCodigo();
		this.preco = obj.getPreco();
		this.prestador = new PrestadorDTO(obj.getPrestador());
		this.servico = new ServicoDTO(obj.getServico());
	}	
}