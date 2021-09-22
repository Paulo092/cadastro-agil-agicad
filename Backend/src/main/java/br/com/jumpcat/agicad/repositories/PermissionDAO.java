package br.com.jumpcat.agicad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jumpcat.agicad.models.Permission;

public interface PermissionDAO extends JpaRepository<Permission, Integer>{
	
}
