package br.com.jumpcat.agicad.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.jumpcat.agicad.models.Servico;
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
@JsonPropertyOrder({"codigo_servico", "nome_servico", "preco_servico", "descricao_obra"}) 
public class ServicoDTO extends RepresentationModel<ServicoDTO> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@JsonProperty("codigo_servico")
	private Integer codigo;

	@NotBlank
	@Size(max = 45)
	@JsonProperty("nome_servico")
	private String nome;

	@Size(max = 255)
	@JsonProperty("descricao_servico")	
	private String descricao;

	public ServicoDTO(Servico obj) {
		this.codigo = obj.getCodigo();
		this.descricao = obj.getDescricao();
		this.nome = obj.getNome();
	}	
}