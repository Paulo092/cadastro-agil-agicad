package br.com.jumpcat.agicad.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRESTADORES")
public class Prestador implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_prestador", nullable = false)
	private Integer codigo;
	
	@Column(name = "endereco_prestador", nullable = false)
	private String endereco;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_codigo", referencedColumnName = "codigo_usuario")
    private Usuario usuario;
	
	@OneToMany(mappedBy = "prestador")
	private List<ServicoPrestado> servicosPrestados;
}
