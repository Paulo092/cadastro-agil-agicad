package br.com.jumpcat.agicad.security;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UsuarioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String login;
	
	private String password;

}
