package br.com.jumpcat.agicad.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
@Table(name = "USUARIOS")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_usuario", nullable = false)
	private Integer codigo;
	
	@Column(name = "login_usuario", nullable = false)
	private String login;
	
	@Column(name = "email_usuario", nullable = false)
	private String email;
	
	@Column(name = "senha_usuario", nullable = false)
	private String senha;
	
	@Column(name = "nome_usuario", nullable = false)
	private String nome;
	
	@OneToOne(mappedBy = "usuario")
    private Prestador prestador;

	public Usuario(Integer codigo, String login, String email, String senha, String nome) {
		this.codigo = codigo;
		this.login = login;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
	}
}
