package br.com.jumpcat.agicad.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SERVICOS")
public class Servico implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Auto Incremento
	@Column(name = "codigo_servico", nullable = false)
	private Integer codigo;
	
	@Size(max = 45)
	@Column(name = "nome_servico", nullable = false)
	private String nome;
	
	@Size(max = 255)
	@Column(name = "descricao_servico")
	private String descricao;
}
