package br.com.jumpcat.agicad.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "PRESTADORES")
public class Prestador implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_pres", nullable = false)
	private Integer codigo;
	
	@Column(name = "endereco_pres", nullable = false)
	private String endereco;
	
	@OneToMany(mappedBy = "prestador")
	private List<Servico> servicos = new ArrayList<>();
	
	@OneToMany(mappedBy = "prestador")
	private List<Agendamentos> agendamentos = new ArrayList<>();

	public Prestador(Integer codigo, String endereco) {
		super();
		this.codigo = codigo;
		this.endereco = endereco;
	}
	
	
}
