package br.com.jumpcat.agicad.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class Usuario implements UserDetails, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_usuario", nullable = false)
	private Integer codigo;
	
//	@Column(name = "login_usuario", nullable = false)
	private String login;
	
//	@Column(name = "email_usuario", nullable = false)
	private String email;
	
//	@Column(name = "senha_usuario", nullable = false)
	private String senha;
	
//	@Column(name = "nome_usuario", nullable = false)
	private String nome;
	
	@OneToOne(mappedBy = "usuario")
    private Prestador prestador;
	
	private Boolean accountNonExpired;
	
	private Boolean accountNonLocked;
	
	private Boolean credentialsNonExpired;
	
	private Boolean enabled;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "userPermission", joinColumns = @JoinColumn(name = "idUser"), 
			   inverseJoinColumns = @JoinColumn(name = "idPermission"))
	private List<Permission> permissions = new ArrayList<>();

	public List<String> getRoles() {
		List<String> roles = new ArrayList<>();
		for(Permission permission : this.permissions) {
			roles.add(permission.getDescricao());
		}
		return roles;
	}

	public Usuario(Integer codigo, String login, String email, String senha, String nome) {
		this.codigo = codigo;
		this.login = login;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}
}
