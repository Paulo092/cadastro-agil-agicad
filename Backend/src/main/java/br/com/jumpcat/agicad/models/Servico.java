package br.com.jumpcat.agicad.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	@Column(name = "nome_servico", nullable = false)
	private String nome;
	
	@Column(name = "preco_servico", nullable = false)
	private Double preco;
	
	@Column(name = "descricao_servico")
	private String descricao;
	
	@ManyToOne
	private Prestador prestador;
	
}
