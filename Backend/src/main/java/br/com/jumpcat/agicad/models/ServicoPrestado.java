package br.com.jumpcat.agicad.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SERVICOS_PRESTADOS")
public class ServicoPrestado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_servico_prestado", nullable = false)
	private Integer codigo;
	
	@Column(name = "preco_servico_prestado", nullable = false)
	private Double preco;

	@ManyToOne
	@JoinColumn(name="codigo_prestador", nullable = false)
	private Prestador prestador;
	
	@ManyToOne
	@JoinColumn(name="codigo_servico", nullable = false)
	private Servico servico;
}
